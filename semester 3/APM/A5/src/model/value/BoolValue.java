package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v) { val=v; }

    public BoolValue() { val=false; }
    
    public boolean getVal() { return val; }
    
    public String toString() { return Boolean.toString(val); }

    @Override
    public Type getType() { return new BoolType(); }

    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolValue)
            return true;
        else
            return false;
    }
}
