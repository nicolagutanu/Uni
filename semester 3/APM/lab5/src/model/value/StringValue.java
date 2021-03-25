package model.value;

import model.type.BoolType;
import model.type.StringType;
import model.type.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String v) { val=v; }

    public StringValue() { val=""; }

    public String getVal() { return val; }

    public String toString() { return val; }

    @Override
    public Type getType() { return new StringType(); }

    @Override
    public boolean equals(Object another) {
        if (another instanceof StringValue)
            return true;
        else
            return false;
    }
}
