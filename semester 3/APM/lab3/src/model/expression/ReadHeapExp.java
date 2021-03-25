package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.exceptions.ExpException;
import model.statement.ReadFileStmt;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExp implements IExpression {
    IExpression exp;

    public ReadHeapExp(IExpression e) { exp=e; }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException {
        Value val=exp.evaluate(sTbl,heap);
        if (val instanceof RefValue) {
            RefValue refVal=(RefValue) val;
            if (heap.contains(refVal.getAddress())) {
                return heap.getLocation(refVal.getAddress());
            }else {
                throw new ExpException("Nonexistent address\n");
            }
        }else {
            throw new ExpException("Expression could not be evaluated to a RefValue");
        }
    }

    @Override
    public String toString() {
        return "rH("+exp+")";
    }
}
