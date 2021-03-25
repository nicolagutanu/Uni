package model.expression;

import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

public class RelationalExp implements IExpression {
    IExpression exp1;
    IExpression exp2;
    int op; //1-<, 2-<=, 3-==, 4-!=, 5->, 6->=

    public RelationalExp(int o, IExpression e1, IExpression e2) {
        exp1=e1;
        exp2=e2;
        op=o;
    }

    @Override
    public Value evaluate(IDictionary<String,Value> sTbl, IHeap<Value> heap) throws ExpException {
        Value val1, val2;
        val1=exp1.evaluate(sTbl, heap);
        if (val1.getType().equals(new IntType())) {
            val2 = exp2.evaluate(sTbl, heap);
            if (val2.getType().equals(new IntType())) {
                IntValue iv1=(IntValue)val1;
                IntValue iv2=(IntValue)val2;
                int nr1, nr2;
                nr1=iv1.getVal();
                nr2=iv2.getVal();
                if (op==1) return new BoolValue(nr1<nr2);
                if (op==2) return new BoolValue(nr1<=nr2);
                if (op==3) return new BoolValue(nr1==nr2);
                if (op==4) return new BoolValue(nr1!=nr2);
                if (op==5) return new BoolValue(nr1>nr2);
                if (op==6) {
                    return new BoolValue(nr1>=nr2);
                }else {
                    throw new ExpException("Unrecognised operation\n");
                }
            }else {
                throw new ExpException("Second operand is not an int\n");
            }
        }else {
            throw new ExpException("First operand is not an int\n");
        }
        //return null;
    }

    @Override
    public String toString() {
        String sym=" ";
        if (op==1)
            sym="<";
        if (op==2)
            sym="<=";
        if (op==3)
            sym="==";
        if (op==4)
            sym="!=";
        if (op==5)
            sym=">";
        if (op==6)
            sym=">=";
        return exp1.toString()+sym+exp2.toString();
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=exp1.typeCheck(typeEnv);
        type2= exp2.typeCheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            }else {
                throw new MyException("Relational Expression: Second operand is not an integer\n");
            }
        }else {
            throw new MyException("Relational Expression: First operand is not an integer\n");
        }
    }
}
