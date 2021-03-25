package model.statement;

import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class SwitchStmt implements IStatement {
    IExpression exp;
    IExpression exp1;
    IStatement stmt1;
    IExpression exp2;
    IStatement stmt2;
    IStatement stmt3;

    public SwitchStmt(IExpression e, IExpression e1, IStatement s1, IExpression e2, IStatement s2, IStatement s3) {
        exp=e;
        exp1=e1;
        stmt1=s1;
        exp2=e2;
        stmt2=s2;
        stmt3=s3;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> exeStack=state.getStack();
        IDictionary<String, Value> symTbl=state.getDictionary();

        IntValue valExp=(IntValue) exp.evaluate(symTbl,state.getHeap());
        IntValue valExp1=(IntValue) exp1.evaluate(symTbl,state.getHeap());
        IntValue valExp2=(IntValue) exp2.evaluate(symTbl, state.getHeap());
        if (valExp.getVal()==valExp1.getVal()) {
            exeStack.push(stmt1);
        }else if (valExp.getVal()== valExp2.getVal()) {
            exeStack.push(stmt2);
        }else
            exeStack.push(stmt3);
        state.setStack(exeStack);
        return null;
    }

    @Override
    public String toString() {
        return "switch("+exp+") (case "+exp1+": "+stmt1+") (case "+exp2+": "+stmt2+") (default: "+stmt3+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        Type typeExp1=exp1.typeCheck(typeEnv);
        Type typeExp2=exp2.typeCheck(typeEnv);
        if (typeExp.equals(typeExp1) && typeExp1.equals(typeExp2) && typeExp2.equals(typeExp)) {
            stmt1.typeCheck(typeEnv.copy());
            stmt2.typeCheck(typeEnv.copy());
            stmt3.typeCheck(typeEnv.copy());
            return typeEnv;
        }else
            throw new MyException("Switch statement: the conditions are not integer");
    }
}
