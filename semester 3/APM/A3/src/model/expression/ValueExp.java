package model.expression;

import model.adt.IDictionary;
import model.exceptions.ExpException;
import model.value.Value;

public class ValueExp implements IExpression {
    Value exp;

    public ValueExp(Value e) { exp=e; }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl) throws ExpException { return exp; }

    @Override
    public String toString() {
        return exp.toString();
    }
}
