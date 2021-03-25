package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class LogicExp implements IExpression {
    IExpression exp1;
    IExpression exp2;
    int op; // 1-and, 2-or

    public LogicExp(int o, IExpression e1, IExpression e2) {
        exp1=e1;
        exp2=e2;
        op=o;
    }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException {
        Value val1, val2;
        val1=exp1.evaluate(sTbl, heap);
        if (val1.getType().equals(new BoolType())) {
            val2=exp2.evaluate(sTbl, heap);
            if (val2.getType().equals(new BoolType())) {
                BoolValue iv1=(BoolValue)val1;
                BoolValue iv2=(BoolValue)val2;
                boolean nr1, nr2;
                nr1=iv1.getVal();
                nr2=iv2.getVal();
                if (op==1) return new BoolValue(nr1&&nr2);
                if (op==2) return new BoolValue(nr1||nr2);
            }else
                throw new ExpException("Second operand is not a boolean\n");
        }else
            throw new ExpException("First operand is not a boolean\n");
        return null;
    }

    @Override
    public String toString() {
        String sym=" ";
        if (op==1)
            sym="and";
        if (op==2)
            sym="or";
        return exp1.toString()+sym+exp2.toString();
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=exp1.typeCheck(typeEnv);
        type2=exp2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            }else {
                throw new MyException("Logic Expression: Second operand is not a boolean\n");
            }
        }else {
            throw new MyException("Logic Expression: First operand is not a boolean\n");
        }
    }
}
