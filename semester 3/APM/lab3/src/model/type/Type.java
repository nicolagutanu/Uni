package model.type;

import model.value.Value;

public interface Type {
    public Value defaultValue();

    public Type copy();
}
