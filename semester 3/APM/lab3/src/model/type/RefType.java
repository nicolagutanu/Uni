package model.type;

import model.value.RefValue;
import model.value.Value;

public class RefType implements Type {
    Type inner;

    public RefType(Type i) { inner=i; }

    public Type getInner() { return inner; }

    public boolean equals(Object another) {
        if (another instanceof RefType)
            return inner.equals((((RefType) another).getInner()));
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public Type copy() {
        return new RefType(inner.copy());
    }

    public String toString() { return "Ref("+inner.toString()+")"; }
}
