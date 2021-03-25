package model.adt;

import model.exceptions.AdtException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock<T,V> implements ILock<T,V> {
    Map<T,V> dict;
    Lock lock=new ReentrantLock();

    public MyLock() { dict=new HashMap<T,V>(); }

    @Override
    public V lookup(T key) {
        lock.lock();
        V k=dict.get(key);
        lock.unlock();
        return k;
    }

    @Override
    public void update(T key, V value) {
        lock.lock();
        dict.put(key,value);
        lock.unlock();
    }

    @Override
    public void remove(T key) throws AdtException {
        lock.lock();
        if (!dict.containsKey(key))
            throw new AdtException("Key doesn't exist in lock table\n");
        dict.remove(key);
        lock.unlock();
    }

    @Override
    public void add(T key, V value) throws AdtException {
        lock.lock();
        if (dict.containsKey(key))
            throw new AdtException("Key already exists\n");
        dict.put(key, value);
        lock.unlock();
    }

    @Override
    public boolean isDefined(T key) {
        lock.lock();
        Boolean contains=dict.containsKey(key);
        lock.unlock();
        return contains;
    }

    @Override
    public void setContent(Map<T,V> cont) {
        lock.lock();
        dict=cont;
        lock.unlock();
    }

    @Override
    public Map<T,V> getContent() {
        lock.lock();
        Map<T,V> copyDict=dict;
        lock.unlock();
        return copyDict;
    }

    @Override
    public String toString() {
        lock.lock();
        String str="";
        for (Map.Entry<T,V> el:dict.entrySet())
            str=str+el.getKey()+"-"+el.getValue()+" ";
        lock.unlock();
        return str;
    }
}
