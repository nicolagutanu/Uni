package model.adt;


import model.exceptions.AdtException;

public interface IDictionary<T, V> {
    public V lookup(T key);
    public void update(T key, V value);
    public void remove(T key) throws AdtException;
    public void add(T key, V value) throws AdtException;
    public boolean isDefined(T key);
}
