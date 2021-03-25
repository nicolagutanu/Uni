package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.exceptions.ExpException;
import model.value.Value;

public interface IExpression {
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException;
    public String toString();
}
