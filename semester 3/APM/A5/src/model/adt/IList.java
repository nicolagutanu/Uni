package model.adt;


import model.exceptions.AdtException;

public interface IList<T> {
    public void add(T item);
    public void remove(T item) throws AdtException, AdtException;
    public int size();
}
