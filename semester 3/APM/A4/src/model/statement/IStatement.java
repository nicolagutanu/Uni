package model.statement;

import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.StmtException;

public interface IStatement {
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException;
    public String toString();
}
