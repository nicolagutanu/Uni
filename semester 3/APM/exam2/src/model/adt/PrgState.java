package model.adt;

import javafx.util.Pair;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.statement.IStatement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    IStack<IStatement> exeStack;
    IDictionary<String, Value> symTable;
    IHeap<Value> heap;
    IList<Value> out;
    IDictionary<StringValue, BufferedReader> fileTable;
    IStatement originalPrg;
    Integer id;
    static Integer counter=0;

    static Integer nextBarrierAddress=0;
    IBarrier<Integer, Pair<Integer, List<Integer>>> barrierTable;

    public PrgState(IStack<IStatement> eStk, IDictionary<String, Value> sTab,  IHeap<Value> h, IList<Value> o, IDictionary<StringValue, BufferedReader> fT, IBarrier<Integer,Pair<Integer,List<Integer>>> bT,IStatement ogPrg){
        id=counter++;
        exeStack=eStk;
        symTable=sTab;
        heap=h;
        out=o;
        originalPrg=ogPrg;
        fileTable=fT;
        barrierTable=bT;
        eStk.push(ogPrg);
    }

    public PrgState(IStack<IStatement> eStk, MyDictionary<String, Value> sTab, MyHeap<Value> h, MyList<Value> o){
        exeStack=eStk;
        symTable=sTab;
        heap=h;
        out=o;
    }

    public IStack<IStatement> getStack() { return exeStack; }
    public IDictionary<String, Value> getDictionary() { return symTable; }
    public IHeap<Value> getHeap() { return heap; }
    public IList<Value> getList() { return out; }
    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }
    public IBarrier<Integer, Pair<Integer, List<Integer>>> getBarrierTable() { return barrierTable; }
    public IStatement getOriginalPrg() { return originalPrg; }
    public Integer getId() { return id; }

    public Integer getNextBarrierAddress() { nextBarrierAddress++; return nextBarrierAddress; }

    public void setStack(IStack<IStatement> eStk) { exeStack=eStk; }
    public void setDictionary(IDictionary<String, Value> sTab) { symTable=sTab; }
    public void setHeap(IHeap<Value> h) { heap=h; }
    public void setList(IList<Value> o) { out=o; }
    public void setOriginalPrg(IStatement ogPrg) { originalPrg=ogPrg; }
    public void setId(int givenId) { id=givenId+1; }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, AdtException, StmtException, ExpException {
        if (exeStack.isEmpty())
            throw new MyException("ExeStack is empty\n");
        IStatement currentStmt= exeStack.pop();
        return currentStmt.execute(this);
    }

    public String toString(){
        String str="Program state: \n";
        str=str+"Id: "+id.toString()+"\n";
        str=str+"Execution stack: "+exeStack.toString()+"\n";
        str=str+"Symbols table: "+symTable.toString()+"\n";
        str=str+"Heap: "+heap.toString()+"\n";
        str=str+"Output: "+out.toString()+"\n";
        str=str+"File table: "+fileTable.toString()+"\n";
        str=str+"Barrier table: "+barrierTable.toString()+"\n";
        return str;
    }
}
