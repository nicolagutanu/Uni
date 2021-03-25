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

public class UnlockStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public UnlockStmt(String v) { var=v; }

    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable= state.getDictionary();
        ILock<Integer,Integer> lockTable= state.getLockTable();;
        IStack<IStatement> exeStack=state.getStack();
        if (symTable.isDefined(var)) {
            Value foundIndex=symTable.lookup(var);
            if(foundIndex.getType().equals(new IntType())) {
                IntValue valFounfIndex=(IntValue) foundIndex;
                if (lockTable.isDefined(valFounfIndex.getVal())) {
                    if (lockTable.lookup(valFounfIndex.getVal())==state.getId()) {
                        lockTable.update(valFounfIndex.getVal(),-1);
                    }
                }else
                    throw new StmtException("Variable not defined in lock table plm");
            }else
                throw new StmtException("Variable is not of type int");
        }else
            throw new StmtException("Variable not defined in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "unlock("+var+")";
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
