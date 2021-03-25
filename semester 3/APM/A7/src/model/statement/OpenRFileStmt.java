package model.statement;

import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.RefType;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class OpenRFileStmt implements IStatement {
    IExpression exp;

    public OpenRFileStmt(IExpression e) { exp=e; }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IDictionary<String, Value> symTbl= state.getDictionary();
        Value val=exp.evaluate(symTbl, state.getHeap());
        if (val.getType().equals(new StringType())) {
            IDictionary<StringValue, BufferedReader> fileTable= state.getFileTable();
            StringValue stringVal=(StringValue) val;
            if (!fileTable.isDefined(stringVal)) {
                try{
                    Reader reader=new FileReader(stringVal.getVal());
                    BufferedReader bF=new BufferedReader(reader);
                    fileTable.update(stringVal, bF);
                }
                catch (IOException e) {
                    throw new StmtException(e.getMessage());
                }
            }else {
                throw new StmtException("The file already exists\n");
            }
        }else {
            throw new StmtException("Given file is not a string");
        }
        return null;
    }

    @Override
    public String toString() {
        return "open("+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        Type typeExp=exp.typeCheck(typeEnv);
        if (typeExp.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("Open File Statement: Given file is not a string\n");
    }
}
