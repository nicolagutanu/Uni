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
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class WhileStmt implements IStatement {
    IExpression exp;
    IStatement stmt;
    
    public WhileStmt(IExpression e, IStatement s) { exp=e; stmt=s; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> stack=state.getStack();
        IDictionary<String, Value> symTbl=state.getDictionary();
        Value val=exp.evaluate(symTbl, state.getHeap());
        if (val.getType().equals(new BoolType())) {
            BoolValue boolVal=(BoolValue) val;
            if (boolVal.getVal()) {
                stack.push(new WhileStmt(exp, stmt));
                stack.push(stmt);
            }
        }else {
            throw new StmtException("Conditional expression is not boolean\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "(while ("+exp+") "+stmt+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            stmt.typeCheck(typeEnv.copy());
            return typeEnv;
        }else
            throw new MyException("While Statement: Conditional expression is not boolean\n");
    }
}
