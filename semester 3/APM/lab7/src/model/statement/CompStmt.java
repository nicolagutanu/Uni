package model.statement;

import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.Type;

public class CompStmt  implements  IStatement {
    IStatement first;
    IStatement second;

    public CompStmt(IStatement is1, IStatement is2) {
        first=is1;
        second=is2;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException {
        IStack<IStatement> stack = state.getStack();
        stack.push(second);
        stack.push(first);
        state.setStack(stack);
        return null;
    }

    @Override
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
