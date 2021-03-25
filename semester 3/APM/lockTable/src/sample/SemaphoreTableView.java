package sample;

import javafx.util.Pair;
import model.value.Value;

import java.util.List;

public class SemaphoreTableView {
    Integer address;
    Pair<Integer, List<Integer>> value;

    public SemaphoreTableView(Integer a, Pair<Integer,List<Integer>> v) {
        address=a;
        value=v;
    }

    public int getAddress() { return address; }

    public Pair<Integer, List<Integer>> getValue() { return value; }

    public void setAddress(int a) { address=a; }

    public void setValue(Pair<Integer, List<Integer>> v) { value=v; }
}
