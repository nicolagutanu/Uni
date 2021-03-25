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

public class AwaitStmt implements IStatement {
    String var;
    static Lock lock=new ReentrantLock();

    public AwaitStmt(String v) { var=v; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTbl=state.getDictionary();
        IBarrier<Integer, BarrierPair> barrierTbl=state.getBarrierTable();
        IStack<IStatement> exeStack=state.getStack();
        if (symTbl.isDefined(var)) {
            IntValue foundIndex= (IntValue) symTbl.lookup(var);
            if (barrierTbl.contains(foundIndex.getVal())) {
                BarrierPair barrierPair=barrierTbl.getValue(foundIndex.getVal());
                int len=barrierPair.getList().size();
                if (barrierPair.getKey()>len) {
                    if (barrierPair.getList().contains(state.getId()))
                        exeStack.push(this);
                    else {
                        barrierPair.getList().add(state.getId());
                        exeStack.push(this);
                    }
                }
            } else
                throw new StmtException("Index nonexistent in barrier table");
        }else
            throw new StmtException("Variable nonexistent in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "await("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
