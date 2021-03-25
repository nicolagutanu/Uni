package model.adt;

import model.exceptions.AdtException;

import java.util.*;

public class MyDictionary<T,V> implements IDictionary<T,V> {
    Map<T,V> dict;

    public MyDictionary() { dict = new HashMap<T,V>(); }

    @Override
    public V lookup(T key) { return dict.get(key); }

    @Override
    public void update(T key, V value) { dict.put(key, value); }

    @Override
    public void remove(T key) throws AdtException {
        if (!dict.containsKey(key))
            throw new AdtException("Key doesn't exist in the dictionary\n");
        dict.remove(key);
    }

    @Override
    public void add(T key, V value) throws AdtException {
        if (dict.containsKey(key))
            throw new AdtException("Key already exists\n");
        dict.put(key, value);
    }

    @Override
    public boolean isDefined(T key) { return dict.containsKey(key); }

    @Override
    public String toString() {
        String str="";
        for (Map.Entry<T, V> el:dict.entrySet())
            str=str+ el.getKey().toString()+"-"+el.getValue().toString()+" ";
        return str;
    }
}
