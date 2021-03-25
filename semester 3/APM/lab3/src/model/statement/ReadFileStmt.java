package model.statement;

import model.adt.IDictionary;
import model.adt.PrgState;
import model.exceptions.AdtException;
import model.exceptions.ExpException;
import model.exceptions.StmtException;
import model.expression.IExpression;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStatement {
    IExpression exp;
    String varName;

    public ReadFileStmt(IExpression e, String vN) {
        exp = e;
        varName = vN;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, AdtException {
        IDictionary<String, Value> symTbl = state.getDictionary();
        if (symTbl.isDefined(varName)) {
            if (symTbl.lookup(varName).getType().equals(new IntType())) {
                Value val = exp.evaluate(symTbl, state.getHeap());
                if (val.getType().equals(new StringType())) {
                    StringValue stringVal = (StringValue) val;
                    IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
                    if (fileTable.isDefined(stringVal)) {
                        BufferedReader bF = fileTable.lookup(stringVal);
                        try {
                            String line = bF.readLine();
                            Value intVal;
                            if (line == null) {
                                intVal = new IntType().defaultValue();
                            } else {
                                try {
                                    intVal = new IntValue(Integer.parseInt(line));
                                }
                                catch (NumberFormatException e){
                                    throw new StmtException("No int in the file");
                                }
                            }
                            symTbl.update(varName, intVal);
                        } catch (IOException e) {
                            throw new StmtException(e.getMessage());
                        }
                    } else {
                        throw new StmtException("No such file exists\n");
                    }
                } else {
                    throw new StmtException("Expression is not a string\n");
                }
            } else {
                throw new StmtException(varName + " is not of type int\n");
            }
        } else {
            throw new StmtException(varName + " is no defined in the symbols table\n");
        }
        return null;
    }


    @Override
    public String toString() {
        return "read from "+exp+" into "+varName;
    }
}
