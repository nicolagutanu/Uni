package model.adt;

import model.exceptions.AdtException;

import java.util.Map;

public interface ILock<T,V> {
    public V lookup(T key);
    public void update(T key, V value);
    public void remove(T key) throws AdtException;
    public void add(T key, V value) throws AdtException;
    public boolean isDefined(T key);
    public void setContent(Map<T,V> cont);
    public Map<T,V> getContent();
}
