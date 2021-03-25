package model.adt;

import model.exceptions.AdtException;

public interface IStack<T> {
    public T pop() throws AdtException;
    public void push(T v);
    public boolean isEmpty();
}
