package model.adt;

import model.exceptions.AdtException;

import java.util.HashMap;
import java.util.Map;

public class MyBarrier<T, V> implements IBarrier<T,V> {
    Map<T,V> barrier;

    public MyBarrier() { barrier=new HashMap<T,V>(); }

    @Override
    public void add(T key, V value) throws AdtException {
        if (barrier.containsKey(key))
            throw new AdtException("Key already exists");
        barrier.put(key,value);
    }

    @Override
    public void remove(T key) throws AdtException {
        if (!barrier.containsKey(key))
            throw new AdtException("Key doesn't exists");
        barrier.remove(key);
    }

    @Override
    public V getValue(T key) {
        return barrier.get(key);
    }

    @Override
    public boolean contains(T key) {
        return barrier.containsKey(key);
    }

    @Override
    public Map<T,V> getContent() {
        return barrier;
    }
}
