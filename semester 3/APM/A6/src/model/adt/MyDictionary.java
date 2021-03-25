package model.adt;

import model.exceptions.AdtException;
import model.value.Value;

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
    public void setContent(Map<T,V> cont) { dict=cont; }

    @Override
    public Map<T,V> getContent() { return dict; }

    @Override
    public String toString() {
        String str="";
        for (Map.Entry<T, V> el:dict.entrySet())
            str=str+ el.getKey().toString()+"-"+el.getValue().toString()+" ";
        return str;
    }

    @Override
    public MyDictionary<T,V> copy() throws AdtException {
        HashMap<T,V> newMap=new HashMap<T,V>(dict);
        MyDictionary<T,V> newDict=new MyDictionary<T,V>();
        newDict.setContent(newMap);
        return newDict;
    }
}
