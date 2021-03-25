package model.statement;

import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.expression.LogicExp;
import model.type.BoolType;
import model.type.Type;

public class RepeatStmt implements IStatement {
    IStatement stmt;
    IExpression exp;

    public RepeatStmt(IStatement s, IExpression e) {
        stmt=s;
        exp=e;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStatement newStmt=new CompStmt(stmt,new WhileStmt(new LogicExp(3,exp),stmt));
        state.getStack().push(newStmt);
        return null;
    }

    @Override
    public String toString() {
        return "repeat "+stmt+" until "+exp;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            stmt.typeCheck(typeEnv.copy());
            return typeEnv;
        }else
            throw new MyException("Repeat Statement: Conditional expression is not boolean\n");
    }
}
