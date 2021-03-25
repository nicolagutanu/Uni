package model.adt;

import model.exceptions.AdtException;

import java.util.*;

public class MyStack<T> implements IStack<T> {
    Stack<T> stack;

    public MyStack() { stack=new Stack<T>(); }

    @Override
    public T pop() throws AdtException {
        if (stack.size()==0)
            throw new AdtException("Stack is empty\n");
        return stack.pop();
    }

    @Override
    public void push(T v) { stack.push(v); }

    @Override
    public boolean isEmpty() { return stack.isEmpty(); }

    @Override
    public String toString() {
        String str="";
        for (T el:stack)
            str=str+el.toString()+" ";
        return str;
    }

    @Override
    public Iterable<T> getAll() {
        if (!stack.isEmpty())
            Collections.reverse(stack);
        return stack;
    }
}
