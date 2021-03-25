package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.type.Type;
import model.value.Value;

public interface IExpression {
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException;
    public String toString();

    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException;
}
