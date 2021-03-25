package model.statement;

import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.RefType;
import model.type.Type;

public class NopStmt implements IStatement {
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException {
        return typeEnv;
    }
}
