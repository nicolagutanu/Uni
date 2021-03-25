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

public class NewLockStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public NewLockStmt(String v) { var=v; }

    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IStack<IStatement> exeStack=state.getStack();
        IDictionary<String, Value> symTbl= state.getDictionary();
        ILock<Integer,Integer> lockTable=state.getLockTable();
        Integer newFreeLocation=state.getNextLockAddress();
        lockTable.add(newFreeLocation,-1);
        if (symTbl.isDefined(var)) {
            if (symTbl.lookup(var).getType().equals(new IntType())) {
                symTbl.update(var,new IntValue(newFreeLocation));
            } else
                throw new StmtException("Variable is not an int");
        }else
            throw new StmtException("Variable is not defined");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newLock("+var+")";
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
