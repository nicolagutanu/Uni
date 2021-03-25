package model.statement;

import javafx.util.Pair;
import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.awt.*;
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

        IntValue number=(IntValue) exp.evaluate(symTable, state.getHeap());
        Integer newFreeLocation=state.getNextBarrierAddress();
        barrierTable.add(newFreeLocation,new Pair<>(number.getVal(),new ArrayList<>()));
        if (symTable.isDefined(var))
            symTable.update(var,new IntValue(newFreeLocation));
        else
            symTable.add(var,new IntValue(newFreeLocation));
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+", "+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
