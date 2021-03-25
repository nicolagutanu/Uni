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

public class AwaitStmt implements IStatement {
    String var;
    Lock lock=new ReentrantLock();

    public AwaitStmt(String v) { var=v; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable=state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        IBarrier<Integer, Pair<Integer, List<Integer>>> barrierTable=state.getBarrierTable();
        if (symTable.isDefined(var)) {
            if (symTable.lookup(var).equals(new IntValue())) {
                IntValue foundIndex=(IntValue) symTable.lookup(var);
                if (barrierTable.isDefined(foundIndex.getVal())) {
                    Pair<Integer,List<Integer>> foundPair=barrierTable.lookup(foundIndex.getVal());
                    Integer nl=foundPair.getValue().size();
                    if (foundPair.getKey()>nl) {
                        if (foundPair.getValue().contains(state.getId())) {
                            exeStack.push(new AwaitStmt(var));
                        }else {
                            foundPair.getValue().add(state.getId());
                            exeStack.push(new AwaitStmt(var));
                        }
                    }
                }else
                    throw new StmtException("Index not found in barrier table");
            }else
                throw new StmtException("Variable is not int");
        }else
            throw new StmtException("Variable not defined in symbols table");
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "await("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar= typeEnv.lookup(var);
        if (typeVar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Await Statement: Variable is not int\n");
    }
}
