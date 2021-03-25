package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int v) { val=v; }

    public IntValue() { val=0; }

    public int getVal() { return val; }

    public String toString() { return Integer.toString(val); }

    @Override
    public Type getType() { return new IntType(); }

    @Override
    public boolean equals(Object another) {
        if (another instanceof IntValue)
            return true;
        else
            return false;
    }
}
