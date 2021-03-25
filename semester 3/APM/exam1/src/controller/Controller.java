package controller;

import com.sun.jdi.InvalidStackFrameException;
import model.adt.IList;
import model.adt.IStack;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.statement.IStatement;
import model.value.RefValue;
import model.value.Value;
import repository.IRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo r) { repo=r; }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTblAddr, Map<Integer,Value> heap) {
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

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgStates) throws MyException, IOException, InterruptedException{
        List<Callable<PrgState>> callList = prgStates.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }).filter(p->p!=null)
                .collect(Collectors.toList());

        prgStates.addAll(newPrgList);
        prgStates.forEach((prg -> {
            try {
                repo.logPrgStateExec(prg);
                System.out.println(prg);
            } catch (MyException | IOException e) {
                System.out.println(e.getMessage());
            }
        }));
        repo.setPrgList(prgStates);
    }

    public void allStep() throws IOException, MyException, InterruptedException{
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getAll());
        while (!prgList.isEmpty()) {
            PrgState state = prgList.get(0);
            state.getHeap().setContent(
                    safeGarbageCollector(getAddrFromSymTbl(prgList, state.getHeap().getContent()),
                            state.getHeap().getContent())
            );
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getAll());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public boolean allStepGUI() throws IOException, MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getAll());
        if (!prgList.isEmpty()) {
            PrgState state = prgList.get(0);
            state.getHeap().setContent(
                    safeGarbageCollector(getAddrFromSymTbl(prgList, state.getHeap().getContent()),
                            state.getHeap().getContent())
            );
            oneStepForAllPrg(prgList);
            return true;
        }else {
            executor.shutdownNow();
            repo.setPrgList(prgList);
            return false;
        }
    }

    public void addState(PrgState state) { repo.add(state); }

    public IRepo getRepo() {
        return repo;
    }
}
