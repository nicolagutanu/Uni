package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class HeapTableView {
    Integer address;
    Value value;

    public HeapTableView(int a, Value v) {
        address=a;
        value=v;
    }

    public int getAddress() { return address; }

    public Value getValue() { return value; }

    public void setAddress(int a) { address=a; }

    public void setValue(Value v) { value=v; }
}
