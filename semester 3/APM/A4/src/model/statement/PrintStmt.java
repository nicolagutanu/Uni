package model.statement;

import model.adt.IList;
import model.adt.IStack;
import model.adt.PrgState;
import model.exceptions.ExpException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.value.Value;

public class PrintStmt implements IStatement {
    IExpression exp;

    public PrintStmt(IExpression e) { exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        //IStack<IStatement> stack= state.getStack();
        IList<Value> out= state.getList();
        out.add(exp.evaluate(state.getDictionary(), state.getHeap()));
        //state.setStack(stack);
        //state.setList(out);
        return state;
    }

    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }
}
