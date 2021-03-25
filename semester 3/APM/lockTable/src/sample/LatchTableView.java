package sample;

public class LatchTableView {
    Integer address;
    Integer value;

    public LatchTableView(int a, int v) {
        address=a;
        value=v;
    }

    public int getAddress() { return address; }

    public Integer getValue() { return value; }

    public void setAddress(int a) { address=a; }

    public void setValue(int v) { value=v; }
}
