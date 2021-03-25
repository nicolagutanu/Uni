package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.StmtException;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.lang.management.BufferPoolMXBean;
import java.util.Map;

public class ForkStmt implements IStatement {
    IStatement stmt;

    public ForkStmt(IStatement s) { stmt=s; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        //IStack<IStatement> stack=state.getStack();
        IDictionary<String, Value> symTbl= state.getDictionary();
        //IDictionary<StringValue, BufferedReader> fileTbl= state.getFileTable();
        //IHeap<Value> heap= state.getHeap();
        //IList<Value> out= state.getList();

        MyStack<IStatement> newStack=new MyStack<IStatement>();

        MyDictionary<String, Value> newSymTbl=new MyDictionary<String, Value>();
        for (Map.Entry<String, Value> entry:symTbl.getContent().entrySet()) {
            newSymTbl.update(new String(entry.getKey()), entry.getValue().copy());
        }
        return new PrgState(newStack, newSymTbl, state.getHeap(), state.getList(), state.getFileTable(), stmt);
    }

    @Override
    public String toString() {
        return "fork("+stmt+")";
    }
}
