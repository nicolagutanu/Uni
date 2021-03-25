package model.statement;

import model.adt.IDictionary;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.BoolType;
import model.type.RefType;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStatement {
    IExpression exp;

    public CloseRFileStmt(IExpression e) { exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IDictionary<String, Value> symTbl= state.getDictionary();
        Value val=exp.evaluate(symTbl, state.getHeap());
        if (val.getType().equals(new StringType())) {
            IDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
            StringValue stringVal=(StringValue) val;
            if (fileTable.isDefined(stringVal)) {
                BufferedReader bF=fileTable.lookup(stringVal);
                try {
                    bF.close();
                    fileTable.remove(stringVal);
                }
                catch (IOException e) {
                    throw new StmtException(e.getMessage());
                }
            }else {
                throw new StmtException("File doesn't exist\n");
            }
        }else {
            throw new StmtException("Expression couldn't be evaluated to a string\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "close("+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeExp.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("Close File Statement: Expression couldn't be evaluated to a string\n");
    }
}
