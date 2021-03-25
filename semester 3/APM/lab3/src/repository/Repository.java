package repository;

import model.adt.PrgState;
import model.exceptions.MyException;
import model.statement.IStatement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepo{
    List<PrgState> states;
    PrgState prg;
    String fileName;

    public Repository() { states=new ArrayList<>(); }

    public Repository(String fN) {
        states=new ArrayList<PrgState>();
        fileName=fN;
    }

    public Repository(PrgState prgState, String fN) {
        prg=prgState;
        fileName=fN;
        states=new ArrayList<PrgState>();
    }

    /*
    @Override
    public PrgState getCrtPrg() { return states.get(states.size()-1); }
     */

    @Override
    public void add(PrgState state) { states.add(state); }

    @Override
    public List<PrgState> getAll() { return states; }

    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException {
        File file=new File(fileName);
        file.createNewFile();
        try{
            FileWriter fileWriter=new FileWriter(file, true);
            fileWriter.write(prgState+"\n");
            fileWriter.close();
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void setPrgList(List<PrgState> s) {
        states=s;
    }
}
