package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.type.Type;
import model.value.Value;

public class ValueExp implements IExpression {
    Value exp;

    public ValueExp(Value e) { exp=e; }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException { return exp; }

    @Override
    public String toString() {
        return exp.toString();
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return exp.getType();
    }
}
