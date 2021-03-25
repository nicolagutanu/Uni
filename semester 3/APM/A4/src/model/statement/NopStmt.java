package model.statement;

import model.adt.PrgState;
import model.exceptions.StmtException;

public class NopStmt implements IStatement {
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return state;
    }
}
