package sample;

import javafx.util.Pair;

import java.util.List;

public class BarrierTableView {
    Integer address;
    Integer value;
    List<Integer> list;

    public BarrierTableView(Integer a, Integer v, List<Integer> l) {
        address=a;
        value=v;
        list=l;
    }

    public int getAddress() { return address; }
    public Integer getValue() { return value; }
    public List<Integer> getList() { return list; }

    public void setAddress(int a) { address=a; }
    public void setValue(Integer v) { value=v; }
    public void setList(List<Integer> l) { list=l; }
}
