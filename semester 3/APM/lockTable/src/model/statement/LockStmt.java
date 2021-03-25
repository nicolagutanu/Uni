package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public LockStmt(String v) { var=v; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable= state.getDictionary();
        ILock<Integer,Integer> lockTable= state.getLockTable();
        IStack<IStatement> exeStack=state.getStack();
        if (symTable.isDefined(var)) {
            Value foundIndex=symTable.lookup(var);
            if(foundIndex.getType().equals(new IntType())) {
                IntValue valFounfIndex=(IntValue) foundIndex;
                if (lockTable.isDefined(valFounfIndex.getVal())) {
                    if (lockTable.lookup(valFounfIndex.getVal())==-1) {
                        lockTable.update(valFounfIndex.getVal(),state.getId());
                    }else
                        exeStack.push(this);
                }else
                    throw new StmtException("Variable not defined in lock table");
            }else
                throw new StmtException("Variable is not of type int");
        }else
            throw new StmtException("Variable not defined in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "lock("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar=typeEnv.lookup(var);
        if (typeVar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("New Lock Statement: the variable is not an int");
    }
}
