package model.adt;

import model.statement.IStatement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class PrgState {
    IStack<IStatement> exeStack;
    IDictionary<String, Value> symTable;
    IList<Value> out;
    IDictionary<StringValue, BufferedReader> fileTable;
    IStatement originalPrg;

    public PrgState(IStack<IStatement> eStk, IDictionary<String, Value> sTab, IList<Value> o, IDictionary<StringValue, BufferedReader> fT, IStatement ogPrg){
        exeStack=eStk;
        symTable=sTab;
        out=o;
        originalPrg=ogPrg;
        fileTable=fT;
        eStk.push(ogPrg);
    }

    public PrgState(IStack<IStatement> eStk, MyDictionary<String, Value> sTab, MyList<Value> o){
        exeStack=eStk;
        symTable=sTab;
        out=o;
    }

    public IStack<IStatement> getStack() { return exeStack; }
    public IDictionary<String, Value> getDictionary() { return symTable; }
    public IList<Value> getList() { return out; }
    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }
    public IStatement getOriginalPrg() { return originalPrg; }

    public void setStack(IStack<IStatement> eStk) { exeStack=eStk; }
    public void setDictionary(IDictionary<String, Value> sTab) { symTable=sTab; }
    public void setList(IList<Value> o) { out=o; }
    public void setOriginalPrg(IStatement ogPrg) { originalPrg=ogPrg; }

    public String toString(){
        String str="Program state: \n";
        str=str+"Execution stack: "+exeStack.toString()+"\n";
        str=str+"Symbols table: "+symTable.toString()+"\n";
        str=str+"Output: "+out.toString()+"\n";
        str=str+"File table: "+fileTable.toString()+"\n";
        return str;
    }
}
