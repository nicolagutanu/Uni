package sample;

import model.value.Value;

public class LockTableView {
    Integer address;
    Integer value;

    public LockTableView(int a, int v) {
        address=a;
        value=v;
    }

    public int getAddress() { return address; }

    public Integer getValue() { return value; }

    public void setAddress(int a) { address=a; }

    public void setValue(int v) { value=v; }
}
