package sample;

import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class SymTableView {
    String variable;
    Value value;

    public SymTableView(String name, Value v) {
        variable=name;
        value=v;
    }

    public String getVariable() { return variable; }
    public Value getValue() { return value; }

    public void setVariable(String n) { variable=n; }
    public void setValue(Value v) { value=v; }
}
