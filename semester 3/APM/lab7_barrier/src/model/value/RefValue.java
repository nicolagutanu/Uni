package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int a, Type lT) { address=a; locationType=lT; }

    public int getAddress() { return address; }

    public Type getLocationType() { return locationType; }

    public String toString() {
        return "("+address+","+locationType+")";
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }
}
