package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class MulExp implements IExpression {
    IExpression exp1;
    IExpression exp2;

    public MulExp(IExpression e1, IExpression e2) { exp1=e1; exp2=e2; }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException {
        Value val1, val2;
        val1=exp1.evaluate(sTbl, heap);
        if (val1.getType().equals(new IntType())) {
            val2=exp2.evaluate(sTbl, heap);
            if (val2.getType().equals(new IntType())) {
                IntValue iv1=(IntValue)val1;
                IntValue iv2=(IntValue)val2;
                int nr1, nr2;
                nr1=iv1.getVal();
                nr2=iv2.getVal();
                return new IntValue((nr1*nr2)-(nr1+nr2));
            }else
                throw new ExpException("Second operand is not an integer\n");
        }else
            throw new ExpException("First operand is not an integer\n");
    }

    @Override
    public String toString() {
        return "MUL("+exp1+","+exp2+")";
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=exp1.typeCheck(typeEnv);
        type2= exp2.typeCheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            }else {
                throw new MyException("Mul Expression: Second operand is not an integer\n");
            }
        }else {
            throw new MyException("Mul Expression: First operand is not an integer\n");
        }
    }
}
