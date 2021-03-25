package model.statement;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStmt implements IStatement {
    String varName;
    IExpression exp;

    public WriteHeapStmt(String vN, IExpression e) { varName=vN; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IDictionary<String, Value> symTbl= state.getDictionary();
        IHeap<Value> heap=state.getHeap();
        if (symTbl.isDefined(varName)) {
            if (symTbl.lookup(varName).getType() instanceof RefType) {
                RefValue refVal=(RefValue) symTbl.lookup(varName);
                if (heap.contains(refVal.getAddress())) {
                    Value val= exp.evaluate(symTbl,heap);
                    if (symTbl.lookup(varName).getType().equals(new RefType(val.getType()))) {
                        int address= refVal.getAddress();
                        heap.update(address, val);
                    }else {
                        throw new StmtException("Types don't match\n");
                    }
                }else {
                    throw new StmtException(varName+" not on heap\n");
                }
            }else {
                throw new StmtException(varName+" not a reference variable\n");
            }
        }else {
            throw new StmtException(varName+" not defined\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "wH("+varName+", "+exp+")";
    }
}
