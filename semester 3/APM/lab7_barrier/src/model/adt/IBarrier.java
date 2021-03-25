package model.adt;

import model.exceptions.AdtException;

import java.util.Map;

public interface IBarrier<T,V> {
    public void add(T key, V value) throws AdtException;
    public void remove(T key) throws AdtException;
    public V getValue(T key);
    public boolean contains(T key);
    public Map<T,V> getContent();
}
