package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

public class SingleValue implements Property{
    private final String value;

    public SingleValue(String origin) {
        this.value = origin;
    }
    public SingleValue() {
        this.value = "";
    }

    @Override
    public String delimiter() {
        return "";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return true;
    }

    @Override
    public String toFile() {
        return value;
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public void add(Property other) {
        throw new UnsupportedOperationException("Cannot add values to single value type");
    }

    @Override
    public void put(Property other) {
        throw new UnsupportedOperationException("Cannot add values to single value type");
    }

    @Override
    public PropertyCollection getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PropertyCollection get(String field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Property getFirst(String field) {
        return  (field.isEmpty()) ? this : null;
    }


}
