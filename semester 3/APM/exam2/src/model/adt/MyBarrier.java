package model.adt;

import model.exceptions.AdtException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBarrier<T,V> implements IBarrier<T,V> {
    Map<T,V> barrier;
    Lock lock=new ReentrantLock();

    public MyBarrier() { barrier=new HashMap<>(); }

    @Override
    public V lookup(T key) {
        lock.lock();
        V value=barrier.get(key);
        lock.unlock();
        return value;
    }

    @Override
    public boolean isDefined(T key) {
        lock.lock();
        Boolean isDef=barrier.containsKey(key);
        lock.unlock();
        return isDef;
    }

    @Override
    public void add(T key, V value) throws AdtException {
        lock.lock();
        if (barrier.containsKey(key))
            throw new AdtException("Key already exists\n");
        barrier.put(key,value);
        lock.unlock();
    }

    @Override
    public Map<T,V> getContent() {
        return barrier;
    }

    @Override
    public String toString() {
        lock.lock();
        String str="";
        for (Map.Entry<T,V> el:barrier.entrySet())
            str=str+el.getKey()+"-"+el.getValue()+" ";
        lock.unlock();
        return str;
    }
}
