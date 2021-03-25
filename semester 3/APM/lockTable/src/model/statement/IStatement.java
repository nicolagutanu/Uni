package model.statement;

import model.adt.MyDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.type.Type;

public interface IStatement {
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException;
    public String toString();

    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException, AdtException;
}
