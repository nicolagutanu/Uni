package sample;

import model.adt.BarrierPair;

public class BarrierTableView {
    Integer index;
    Integer value;
    String list;

    public BarrierTableView(Integer i, Integer v, String l) { index=i; value=v; list=l; }

    public Integer getIndex() { return index; }
    public Integer getValue() { return value; }
    public String getList() { return list; }

    public void setIndex(Integer i) { index=i; }
    public void setValue(Integer v) { value=v; }
    public void setList(String l) { list=l; }
}
