package model.statement;

import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.Type;
import model.value.Value;

public class AssignStmt implements IStatement {
    String var;
    IExpression exp;

    public AssignStmt(String v, IExpression e) { var=v; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        IDictionary<String, Value> symTbl= state.getDictionary();
        if (symTbl.isDefined(var)) {
            Value val=exp.evaluate(symTbl, state.getHeap());
            Type typeVar=symTbl.lookup(var).getType();
            if (val.getType().equals(typeVar))
                symTbl.update(var, val);
            else
                throw new StmtException("Variable type and type of expression don't match\n");
        }else
            throw new StmtException("The used variable wasn't declared before\n");
        return null;
    }

    @Override
    public String toString(){
        return var+"="+exp.toString();
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar= typeEnv.lookup(var);
        Type typeExp= exp.typeCheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new MyException("Assignment Statement: Assignment sides have different types\n");
    }
}
