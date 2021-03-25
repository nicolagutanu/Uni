package model.statement;

import javafx.util.Pair;
import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public ReleaseStmt(String v) { var=v; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable= state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        ISemaphore<Integer, Pair<Integer, List<Integer>>> semaphoreTable=state.getSemaphoreTable();
        if (symTable.isDefined(var)) {
            if (symTable.lookup(var).getType().equals(new IntType())) {
                IntValue foundIndex=(IntValue) symTable.lookup(var);
                if (semaphoreTable.isDefined(foundIndex.getVal())) {
                    Pair<Integer,List<Integer>> foundPair=semaphoreTable.lookup(foundIndex.getVal());
                    if (foundPair.getValue().contains(state.getId())) {
                        foundPair.getValue().remove(state.getId());
                    }
                }else
                    throw new StmtException("Index not found in semaphore table");
            }else
                throw new StmtException("Variable is not an int type");
        }else
            throw new StmtException("Variable not defined in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "release("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar=typeEnv.lookup(var);
        if (typeVar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Create Semaphore Statement: the variable is not an int");
    }
}
