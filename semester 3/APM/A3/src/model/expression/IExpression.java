package model.expression;

import model.adt.IDictionary;
import model.exceptions.ExpException;
import model.value.Value;

public interface IExpression {
    public Value evaluate(IDictionary<String,Value> sTbl) throws ExpException;
    public String toString();
}
