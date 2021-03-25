package model.expression;

import model.adt.IDictionary;
import model.exceptions.ExpException;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

public class ArithExp implements IExpression {
    IExpression exp1;
    IExpression exp2;
    char op; //1-plus, 2-minus, 3-multiply, 4-divide

    public ArithExp(char o, IExpression e1, IExpression e2) {
        exp1=e1;
        exp2=e2;
        op=o;
    }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl) throws ExpException {
        Value val1, val2;
        val1=exp1.evaluate(sTbl);
        if (val1.getType().equals(new IntType())) {
            val2=exp2.evaluate(sTbl);
            if (val2.getType().equals(new IntType())) {
                IntValue iv1=(IntValue)val1;
                IntValue iv2=(IntValue)val2;
                int nr1, nr2;
                nr1=iv1.getVal();
                nr2=iv2.getVal();
                if (op=='+') return new IntValue(nr1+nr2);
                if (op=='-') return new IntValue(nr1-nr2);
                if (op=='*') return new IntValue(nr1*nr2);
                if (op=='/')
                    if (nr2==0) throw new ExpException("Impossible division by 0\n");
                    else
                        return new IntValue(nr1/nr2);
            }else
                throw new ExpException("Second operand is not an integer\n");
        }else
            throw new ExpException("First operand is not an integer\n");
        return null;
    }

    @Override
    public String toString() {
        return exp1.toString()+op+exp2.toString();
    }
}
