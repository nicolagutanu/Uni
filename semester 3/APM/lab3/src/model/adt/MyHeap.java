package model.adt;

import model.exceptions.AdtException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap<V> implements IHeap<V> {
    Map<Integer,V> heap;
    Integer freeLocation=0;
    
    public MyHeap() {
        heap=new HashMap<Integer,V>();
        //freeLocation=0;
    }

    @Override
    public int allocate(V value) {
        freeLocation=freeLocation+1;
        heap.put(freeLocation,value);
        return freeLocation;
    }

    @Override
    public void deallocate(int address) {
        heap.remove(address);
    }

    @Override
    public void add(Integer address, V content) throws AdtException {
        if (heap.containsKey(address))
            throw new AdtException("Content already exists\n");
        heap.put(address,content);
    }

    @Override
    public void update(int address, V content) {
        heap.put(address, content);
    }

    @Override
    public boolean contains(int address) {
        if (heap.containsKey(address))
            return true;
        return false;
    }

    @Override
    public V getLocation(int address) {
        return heap.get(address);
    }

    @Override
    public void setContent(Map<Integer,V> h) {
        heap=h;
    }

    @Override
    public Map<Integer,V> getContent() { return heap; }

    @Override
    public String toString() {
        String str="";
        for (Map.Entry<Integer,V> el:heap.entrySet()) {
            str=str+el.getKey()+"-"+el.getValue()+" ";
        }
        return str;
    }
}
