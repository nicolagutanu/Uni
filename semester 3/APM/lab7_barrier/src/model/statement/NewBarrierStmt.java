package model.statement;

import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.IntType;
import model.type.RefType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements IStatement {
    String var;
    IExpression exp;
    static Lock lock=new ReentrantLock();

    public NewBarrierStmt(String v, IExpression e) { var=v; exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        lock.lock();
        IDictionary<String, Value> symTbl= state.getDictionary();
        IBarrier<Integer, BarrierPair> barrierTbl=state.getBarrierTable();
        IntValue nr=(IntValue)exp.evaluate(symTbl,state.getHeap());
        int n=nr.getVal();
        Integer newFreeLocation=state.getNewFreeLocation();
        barrierTbl.add(newFreeLocation,new BarrierPair(n));
        symTbl.add(var, new IntValue(newFreeLocation));
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+","+exp.toString()+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
