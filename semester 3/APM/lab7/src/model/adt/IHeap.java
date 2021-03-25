package model.adt;

import model.exceptions.AdtException;

import java.util.Map;

public interface IHeap<V> {
    public int allocate(V value);
    public void deallocate(int address);
    public void add(Integer address, V content) throws AdtException;
    public void update(int address, V content);
    public boolean contains(int address);
    public V getLocation(int address);
    public void setContent(Map<Integer,V> h);
    public Map<Integer,V> getContent();
}
