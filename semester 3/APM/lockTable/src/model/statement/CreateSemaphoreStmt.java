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

public class CreateSemaphoreStmt implements IStatement {
    String var;
    IExpression exp;
    Lock lock=new ReentrantLock();

    public CreateSemaphoreStmt(String v, IExpression e) { var=v; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable= state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        ISemaphore<Integer, Pair<Integer, List<Integer>>> semaphoreTable=state.getSemaphoreTable();
        Value val=exp.evaluate(symTable, state.getHeap());
        if (symTable.isDefined(var)) {
            if (symTable.lookup(var).getType().equals(new IntType())) {
                if (val.getType().equals(new IntType())) {
                    IntValue number1=(IntValue) val;
                    Integer newFreeLocation=state.getNextSemaphoreAddress();
                    semaphoreTable.add(newFreeLocation, new Pair<>(number1.getVal(),new ArrayList<>()));
                    symTable.update(var,new IntValue(newFreeLocation));
                }else
                    throw new StmtException("Expression doesn't evaluate to an int type");
            }else
                throw new StmtException("Variable is not an int type");
        }else
            throw new StmtException("Variable not in symbols table");

        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "createSemaphore("+var+","+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeVar= typeEnv.lookup(var);
        Type typeExp= exp.typeCheck(typeEnv);
        if (typeVar.equals(new IntType())) {
            if (typeExp.equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("Create Semaphore Statement: expression is not an int");
        } else
            throw new MyException("Create Semaphore Statement: Variable is not an int");
    }
}
