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

public class SymStmt implements IStatement {
    String var;
    IExpression exp1;
    IExpression exp2;
    IExpression exp3;

    public SymStmt(String v, IExpression e1, IExpression e2, IExpression e3) {
        var=v;
        exp1=e1;
        exp2=e2;
        exp3=e3;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> exeStack=state.getStack();
        IStatement thenS=new AssignStmt(var,exp2);
        IStatement elseS=new AssignStmt(var,exp3);
        IStatement statement=new IfStmt(exp1,thenS,elseS);
        exeStack.push(statement);
        state.setStack(exeStack);
        return null;
    }

    @Override
    public String toString() {
        return var+"="+exp1+"?"+exp2+":"+exp3;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp1=exp1.typeCheck(typeEnv);
        Type typeExp2=exp2.typeCheck(typeEnv);
        Type typeExp3=exp3.typeCheck(typeEnv);

        Type typeVar= typeEnv.lookup(var);
        if (typeVar.equals(typeExp2) && typeVar.equals(typeExp3))
            if (typeExp1.equals(new BoolType()))
                return typeEnv;
            else throw new MyException("Symbol Statement: Expression is no boolean");
        else
            throw new MyException("Symbol Statement: Assignment sides have different types\n");
    }
}
