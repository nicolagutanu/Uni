package model.statement;

import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class IfStmt implements IStatement {
    IExpression exp;
    IStatement thenS;
    IStatement elseS;

    public IfStmt(IExpression e, IStatement tS, IStatement eS) {
        exp=e;
        thenS=tS;
        elseS=eS;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        IStack<IStatement> stack=state.getStack();
        Value cond= exp.evaluate(state.getDictionary(), state.getHeap());
        if (cond.getType().equals(new BoolType())) {
            BoolValue boolCond=(BoolValue) cond;
            if (boolCond.getVal()==true)
                stack.push(thenS);
            else if (boolCond.getVal()==false)
                stack.push(elseS);
        } else
            throw new StmtException("Condition is not boolean");
        return null;
    }

    @Override
    public String toString() {
        return "if ("+ exp.toString()+") then (" +thenS.toString() +") else ("+elseS.toString()+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            thenS.typeCheck(typeEnv.copy());
            elseS.typeCheck(typeEnv.copy());
            return typeEnv;
        }else
            throw new MyException("If Statement: The IF condition is not boolean\n");
    }
}
