package model.statement;

import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.expression.RelationalExp;
import model.expression.VarExp;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;

public class ForStmt implements IStatement {
    String var;
    IExpression exp1;
    IExpression exp2;
    IExpression exp3;
    IStatement stmt;

    public ForStmt(String v, IExpression e1, IExpression e2, IExpression e3, IStatement s) {
        var=v;
        exp1=e1;
        exp2=e2;
        exp3=e3;
        stmt=s;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> exeStack= state.getStack();
        IStatement statement= new CompStmt(new AssignStmt(var,exp1), new WhileStmt(new RelationalExp(1,new VarExp(var),exp2), new CompStmt(stmt,new AssignStmt(var,exp3))));
        exeStack.push(statement);
        state.setStack(exeStack);
        return null;
    }

    @Override
    public String toString() {
        return "for("+var+"="+exp1+";"+var+"<"+exp2+";"+var+"="+exp3+")"+stmt;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp1=exp1.typeCheck(typeEnv);
        Type typeExp2=exp2.typeCheck(typeEnv);
        Type typeExp3=exp3.typeCheck(typeEnv);

        Type typeVar= typeEnv.lookup(var);
        if (typeVar.equals(new IntType()) && typeExp1.equals(new IntType()) && typeExp2.equals(new IntType()) && typeExp3.equals(new IntType())) {
            stmt.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new MyException("For Statement: Assignment sides have different types\n");
    }
}
