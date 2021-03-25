package model.adt;

import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.statement.IStatement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class PrgState {
    IStack<IStatement> exeStack;
    IDictionary<String, Value> symTable;
    IHeap<Value> heap;
    IList<Value> out;
    IDictionary<StringValue, BufferedReader> fileTable;
    IStatement originalPrg;
    static int id=0;

    public PrgState(IStack<IStatement> eStk, IDictionary<String, Value> sTab,  IHeap<Value> h, IList<Value> o, IDictionary<StringValue, BufferedReader> fT, IStatement ogPrg){
        exeStack=eStk;
        symTable=sTab;
        heap=h;
        out=o;
        originalPrg=ogPrg;
        fileTable=fT;
        id=getId();
        eStk.push(ogPrg);
    }

    public PrgState(IStack<IStatement> eStk, MyDictionary<String, Value> sTab, MyHeap<Value> h, MyList<Value> o){
        exeStack=eStk;
        symTable=sTab;
        heap=h;
        out=o;
    }

    public static synchronized int getId() {
        ++id;
        return id;
    }

    public IStack<IStatement> getStack() { return exeStack; }
    public IDictionary<String, Value> getDictionary() { return symTable; }
    public IHeap<Value> getHeap() { return heap; }
    public IList<Value> getList() { return out; }
    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }
    public IStatement getOriginalPrg() { return originalPrg; }

    public void setStack(IStack<IStatement> eStk) { exeStack=eStk; }
    public void setDictionary(IDictionary<String, Value> sTab) { symTable=sTab; }
    public void setHeap(IHeap<Value> h) { heap=h; }
    public void setList(IList<Value> o) { out=o; }
    public void setOriginalPrg(IStatement ogPrg) { originalPrg=ogPrg; }

    public boolean isNotCompleted() {
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
        str=str+"Id: "+id+"\n";
        str=str+"Execution stack: "+exeStack.toString()+"\n";
        str=str+"Symbols table: "+symTable.toString()+"\n";
        str=str+"Heap: "+heap.toString()+"\n";
        str=str+"Output: "+out.toString()+"\n";
        str=str+"File table: "+fileTable.toString()+"\n";
        return str;
    }
}
