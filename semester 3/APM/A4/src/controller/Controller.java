package controller;

import com.sun.jdi.InvalidStackFrameException;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    IRepo repo;

    public Controller(IRepo r) { repo=r; }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTblAddr, List<Integer> heapAddr, Map<Integer,Value> heap) {
        return heap.entrySet().stream()
                .filter(e->symTblAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTbl(Collection<Value> symTblValues) {
        return symTblValues.stream()
                .filter(v->v instanceof RefValue)
                .map((v->{RefValue v1=(RefValue) v; return v1.getAddress();}))
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v->v instanceof RefValue)
                .map((v->{RefValue v1=(RefValue) v; return v1.getAddress();}))
                .collect(Collectors.toList());
    }

    public PrgState oneStep(PrgState state) throws MyException, AdtException, StmtException, ExpException {
        IStack<IStatement> stack=state.getStack();
        if (stack.isEmpty())
            throw new MyException("ExeStack is empty\n");
        IStatement currentStmt= stack.pop();
        return currentStmt.execute(state);
    }

    public void allStep() throws MyException, IOException {
        PrgState prg=repo.getCrtPrg();
        repo.logPrgStateExec();
        while (!prg.getStack().isEmpty()){
            try{
                oneStep(prg);
                System.out.println(prg.toString());
                prg.getHeap().setContent(safeGarbageCollector(
                        getAddrFromSymTbl(prg.getDictionary().getContent().values()),
                        getAddrFromHeap(prg.getHeap().getContent().values()),
                        prg.getHeap().getContent()
                ));
                repo.logPrgStateExec();
            } catch (MyException|AdtException|StmtException|ExpException exception){
                throw new MyException(exception.getMessage());
            }
        }
    }

    public void addState(PrgState state) { repo.add(state); }
}
