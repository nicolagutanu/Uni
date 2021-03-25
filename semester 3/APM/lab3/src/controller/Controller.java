package controller;

import model.adt.PrgState;
import model.exceptions.MyException;
import model.value.RefValue;
import model.value.Value;
import repository.IRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo r) { repo=r; }

    public Map<Integer, Value> conservativeGarbageCollector(List<Integer> symTblAddr, Map<Integer,Value> heap) {
        return heap.entrySet().stream()
                .filter(e->symTblAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTbl(List<PrgState> prgList, Map<Integer, Value> heap) {
        List<Collection<Value>> symTblValues=prgList.stream()
                .map(prgState->prgState.getDictionary().getContent().values())
                .collect(Collectors.toList());
        List<Integer> symTblAddrs=new ArrayList<>();
        symTblValues.forEach(symTbl->symTbl.stream()
        .filter(v->v instanceof RefValue)
        .forEach(v->{
            while (v instanceof RefValue) {
                symTblAddrs.add(((RefValue)v).getAddress());
                v=heap.get(((RefValue)v).getAddress());
            }
        }));
        return symTblAddrs;
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v->v instanceof RefValue)
                .map((v->{RefValue v1=(RefValue) v; return v1.getAddress();}))
                .collect(Collectors.toList());
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException, IOException, InterruptedException {
        List<Callable<PrgState>> callList=prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList=executor.invokeAll(callList).stream()
                .map(future->{try {
                    return future.get();
                }
                catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
                return null;
                })
                .filter(p->p!=null)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException|IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(prgList);
    }

    public void allStep() throws IOException, MyException, InterruptedException {
        executor= Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getAll());
        PrgState state=prgList.get(0);
        while(prgList.size()>0) {
            state.getHeap().setContent(conservativeGarbageCollector(getAddrFromSymTbl(prgList, state.getHeap().getContent()), state.getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getAll());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public void addState(PrgState state) { repo.add(state); }
}
