package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public CountDownStmt(String v) { var=v; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable=state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        ILatch<Integer,Integer> latchTable=state.getLatchTable();
        if (symTable.isDefined(var)) {
            IntValue foundIndex=(IntValue) symTable.lookup(var);
            if (latchTable.isDefined(foundIndex.getVal())) {
                if (latchTable.lookup(foundIndex.getVal())>0) {
                    latchTable.update(foundIndex.getVal(),latchTable.lookup(foundIndex.getVal())-1);
                    state.getList().add(new IntValue(state.getId()));
                }
            }
        }else
            throw new StmtException("Variable not defined in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "countDown("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
