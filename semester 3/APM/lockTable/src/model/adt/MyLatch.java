package model.adt;

import model.exceptions.AdtException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLatch<T,V> implements ILatch<T,V> {
    Map<T,V> latch;
    Lock lock=new ReentrantLock();

    public MyLatch() { latch=new HashMap<>(); }

    @Override
    public V lookup(T key) {
        lock.lock();
        V value=latch.get(key);
        lock.unlock();
        return value;
    }

    @Override
    public boolean isDefined(T key) {
        lock.lock();
        Boolean isDef=latch.containsKey(key);
        lock.unlock();
        return isDef;
    }

    @Override
    public void add(T key, V value) throws AdtException {
        lock.lock();
        if (latch.containsKey(key))
            throw new AdtException("Key already exists");
        latch.put(key,value);
        lock.unlock();
    }

    @Override
    public void update(T key, V value) {
        lock.lock();
        latch.put(key,value);
        lock.unlock();
    }

    @Override
    public Map<T,V> getContent() { return latch; }

    @Override
    public String toString() {
        lock.lock();
        String str="";
        for (Map.Entry<T,V> el:latch.entrySet())
            str=str+el.getKey()+"-"+el.getValue()+" ";
        lock.unlock();
        return str;
    }
}
