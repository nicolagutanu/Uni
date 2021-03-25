package repository;

import model.adt.PrgState;
import model.exceptions.MyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IRepo {
    //public PrgState getCrtPrg();
    public void add(PrgState state);
    public List<PrgState> getAll();

    public void deleteFromFile() throws FileNotFoundException;
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException;

    public List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> prgList);
}
