package model.statement;

import javafx.util.Pair;
import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements IStatement {
    String var;
    IExpression exp;
    Lock lock=new ReentrantLock();

    public NewBarrierStmt(String v, IExpression e) { var=v; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable=state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        IBarrier<Integer, Pair<Integer, List<Integer>>> barrierTable=state.getBarrierTable();

        Value number=exp.evaluate(symTable, state.getHeap());
        if (number.getType().equals(new IntType())) {
            if (symTable.isDefined(var)) {
                IntValue nr=(IntValue) number;
                Integer newFreeLocation=state.getNextBarrierAddress();
                barrierTable.add(newFreeLocation,new Pair<>(nr.getVal(),new ArrayList<>()));
                symTable.update(var,new IntValue(newFreeLocation));
            }
            else
                throw new StmtException("Variable not in symbols table");
        }else
            throw new StmtException("Expression is not an int");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+", "+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar= typeEnv.lookup(var);
        Type typeExp= exp.typeCheck(typeEnv);
        if (typeVar.equals(new IntType()))
            if (typeExp.equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("New Barrier Statement: Expression is not int\n");
        else
            throw new MyException("New Barrier Statement: Variable is not int\n");
    }
}
