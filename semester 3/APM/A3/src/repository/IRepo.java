package repository;

import model.adt.PrgState;
import model.exceptions.MyException;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    public PrgState getCrtPrg();
    public void add(PrgState state);
    public List<PrgState> getAll();

    public void logPrgStateExec() throws MyException, IOException;
}
