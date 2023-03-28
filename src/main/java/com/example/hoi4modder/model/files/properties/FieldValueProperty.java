package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import com.example.hoi4modder.model.files.properties.lists.PropertyList;

public class FieldValueProperty implements Property {
    private final String value;
    private final String field;
    public FieldValueProperty() {
        field = "";
        value = "";
    }
    public FieldValueProperty(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String delimiter() {
        return " = ";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.contains("=");
    }

    @Override
    public String toFile() {
        return field + delimiter() + value;
    }

    @Override
    public String name() {
        return field;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public void add(Property other) {
        throw new UnsupportedOperationException("Cannot add values to FieldValue");
    }

    @Override
    public void put(Property other) {
        throw new UnsupportedOperationException("Cannot put values to FieldValue");
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
        return (field.equals(this.name())) ? this : null;
    }

}
