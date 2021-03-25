package model.statement;

import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.StmtException;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

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
        else if (type.equals(new IntType()))
            symTable.add(name, new IntType().defaultValue());
        else if (type.equals(new BoolType()))
            symTable.add(name, new BoolType().defaultValue());
        else if (type.equals(new StringType()))
            symTable.add(name, new StringType().defaultValue());
        else
            throw new StmtException("Type doesn't exist\n");
        state.setDictionary(symTable);
        state.setStack(stack);
        return state;
    }

    @Override
    public String toString(){
        return type.toString()+" "+name;
    }
}
