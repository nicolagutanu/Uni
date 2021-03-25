package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStmt implements IStatement {
    String var;
    IExpression exp;
    Lock lock=new ReentrantLock();

    public NewLatchStmt(String v, IExpression e) { var=v; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTable= state.getDictionary();
        IStack<IStatement> exeStack= state.getStack();
        ILatch<Integer,Integer> latchTable= state.getLatchTable();

        IntValue number=(IntValue) exp.evaluate(symTable,state.getHeap());
        Integer newFreeLocation=state.getNextLatchAddress();
        latchTable.add(newFreeLocation, number.getVal());
        if (symTable.isDefined(var))
            symTable.update(var, new IntValue(newFreeLocation));
        else
            symTable.add(var,new IntValue(newFreeLocation));
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newLatch("+var+","+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
