package model.adt;

import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

import java.util.ArrayList;
import java.util.List;

public class BarrierPair {
    Integer key;
    List<Integer> list=new ArrayList<>();

    public BarrierPair(Integer k, List<Integer> l) {
        key=k;
        list=l;
    }

    public BarrierPair(Integer k) { key=k; }

    public Integer getKey() { return key; }
    public List<Integer> getList() { return list; }

    public void setKey(Integer k) { key=k; }
    public void setList(List<Integer> l) { list=l; }

    @Override
    public String toString() {
        String str="";
        for (Integer e:list)
            str=str+e.toString()+",";
        return key+"-{"+str+"}";
    }
}
