package model.type;

import model.value.BoolValue;
import model.value.Value;

public class BoolType implements Type {

    public boolean equals(Object another) {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type copy() {
        return new BoolType();
    }

    public String toString() { return "bool"; }
}
