package model.statement;

import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.StmtException;
import model.type.*;
import model.value.*;

public class VarDeclStmt implements IStatement {
    String name;
    Type type;

    public VarDeclStmt(String n, Type t) { name=n; type=t;}

    @Override
    public PrgState execute(PrgState state) throws StmtException, AdtException {
        IStack<IStatement> stack=state.getStack();
        IDictionary<String, Value> symTable= state.getDictionary();;
        if (symTable.isDefined(name))
            throw new StmtException("Variable already exists\n");
        else {
            symTable.add(name, type.defaultValue());
        }
        /*
        else if (type.equals(new IntType()))
            symTable.add(name, new IntType().defaultValue());
        else if (type.equals(new BoolType()))
            symTable.add(name, new BoolType().defaultValue());
        else if (type.equals(new StringType()))
            symTable.add(name, new StringType().defaultValue());
        else if (type.equals(new RefType()))
            symTable.add(name, new RefType().defaultValue());
        else
            throw new StmtException("Type doesn't exist\n");
         */
        state.setDictionary(symTable);
        state.setStack(stack);
        return null;
    }

    @Override
    public String toString(){
        return type.toString()+" "+name;
    }
}
