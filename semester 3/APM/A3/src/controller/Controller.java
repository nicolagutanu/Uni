package controller;

import com.sun.jdi.InvalidStackFrameException;
import model.adt.IStack;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.statement.IStatement;
import repository.IRepo;

import java.io.IOException;

public class Controller {
    IRepo repo;

    public Controller(IRepo r) { repo=r; }

    public PrgState oneStep(PrgState state) throws MyException, AdtException, StmtException, ExpException {
        IStack<IStatement> stack=state.getStack();
        if (stack.isEmpty())
            throw new MyException("ExeStack is empty\n");
        IStatement currentStmt= stack.pop();
        return currentStmt.execute(state);
    }

    public void allStep() throws MyException, IOException {
        PrgState prg=repo.getCrtPrg();
        repo.logPrgStateExec();
        while (!prg.getStack().isEmpty()){
            try{
                oneStep(prg);
                System.out.println(prg.toString());
                repo.logPrgStateExec();
            } catch (MyException|AdtException|StmtException|ExpException exception){
                throw new MyException(exception.getMessage());
            }
        }
    }

    public void addState(PrgState state) { repo.add(state); }
}
