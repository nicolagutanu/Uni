package model.statement;

import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.ValueExp;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

public class WaitStmt implements IStatement {
    Value number;

    public WaitStmt(Value n) { number=n; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> stack=state.getStack();
        if (number.getType().equals(new IntType())) {
            IntValue intVal=(IntValue) number;
            if (intVal.getVal()>0) {
                stack.push(new WaitStmt(new IntValue(intVal.getVal()-1)));
                stack.push(new PrintStmt(new ValueExp(number)));
            }
        }else {
            throw new StmtException("Value of wait is not int\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "wait("+number+");";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeNr=number.getType();
        if (typeNr.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Wait Statement: not an int type");
    }
}
