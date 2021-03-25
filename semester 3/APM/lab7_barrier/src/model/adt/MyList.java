package model.adt;

import model.exceptions.AdtException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {
    List<T> list;

    public MyList() { list = new ArrayList<T>(); }

    @Override
    public void add(T item) { list.add(item); }

    @Override
    public void remove(T item) throws AdtException {
        if (!list.contains(item))
            throw new AdtException("Element doesn't exist\n");
        list.remove(item);
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public String toString() {
        String str="";
        for (T el:list)
            str=str+el.toString()+" ";
        return str;
    }

    @Override
    public Iterable<T> getAll() { return list; }
}
