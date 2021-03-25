package model.adt;

import model.exceptions.AdtException;

import java.util.Map;

public interface ILatch<T,V> {
    public V lookup(T key);
    public boolean isDefined(T key);
    public void add(T key, V value) throws AdtException;
    public void update(T key, V value);
    public Map<T,V> getContent();
}
