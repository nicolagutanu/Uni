package model.statement;

import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.ValueExp;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class SleepStmt implements IStatement {
    Value number;

    public SleepStmt(Value n) { number=n; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IStack<IStatement> stack=state.getStack();
        if (number.getType().equals(new IntType())) {
            IntValue intVal=(IntValue) number;
            if (intVal.getVal()>0) {
                stack.push(new SleepStmt(new IntValue(intVal.getVal()-1)));
            }
        }else {
            throw new StmtException("Value of wait is not int\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "sleep("+number+");";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeNr=number.getType();
        if (typeNr.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Sleep Statement: not an int type");
    }
}
