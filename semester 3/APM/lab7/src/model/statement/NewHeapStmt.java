package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

import java.sql.Statement;

public class NewHeapStmt implements IStatement {
    String varName;
    IExpression exp;

    public NewHeapStmt(String vN, IExpression e) {
        varName=vN;
        exp=e;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IDictionary<String, Value> symTbl= state.getDictionary();
        IHeap<Value> heap=state.getHeap();
        IStack<IStatement> stack= state.getStack();
        if (symTbl.isDefined(varName)) {
            if (symTbl.lookup(varName).getType() instanceof RefType) {
                Value val=exp.evaluate(symTbl, state.getHeap());
                Value locationType=symTbl.lookup(varName);
                if(val.getType().equals(((RefType)(locationType.getType())).getInner())) {
                    int address= heap.allocate(val);
                    symTbl.update(varName, new RefValue(address, val.getType()));
                }else {
                    throw new StmtException("Types don't match\n");
                }
            }else {
                throw new StmtException(varName+" is not a reference\n");
            }
        }else {
            throw new StmtException(varName+" is not defined\n");
        }
        state.setDictionary(symTbl);
        state.setHeap(heap);
        state.setStack(stack);
        return null;
    }

    @Override
    public String toString() {
        return "new("+varName+", "+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar=typeEnv.lookup(varName);
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("New Heap Statement: New statement sides have different types\n");
    }
}
