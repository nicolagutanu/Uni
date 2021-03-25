package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.Type;
import model.value.Value;

import java.util.Map;

public class ForkStmt implements IStatement {
    IStatement stmt;

    public ForkStmt(IStatement s) { stmt=s; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> newStack=new MyStack<>();
        IDictionary<String, Value> symTbl= state.getDictionary();

        MyDictionary<String, Value> newSymTbl=new MyDictionary<String, Value>();
        for (Map.Entry<String, Value> entry:symTbl.getContent().entrySet()) {
            newSymTbl.add(new String(entry.getKey()), entry.getValue());
        }
        return new PrgState(newStack, newSymTbl, state.getHeap(), state.getList(), state.getFileTable(), state.getBarrierTable(),stmt);
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        stmt.typeCheck(typeEnv.copy());
        return typeEnv;
    }
}
