package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.exceptions.ExpException;
import model.value.Value;

public class VarExp implements IExpression {
    String var;

    public VarExp(String v) { var=v; }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException {
        if (sTbl.isDefined(var))
            return sTbl.lookup(var);
        else throw new ExpException("Variable is not defined\n");
    }
    public String toString(){
        return var;
    }

}
