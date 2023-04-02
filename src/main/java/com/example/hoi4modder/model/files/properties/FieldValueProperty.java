package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

/**
 * Property with named value
 * Uses name=value format
 */
public class FieldValueProperty implements Property {
    private final String value;
    private final String field;

    /**
     * Empty property. Nothing equals anything
     */
    public FieldValueProperty() {
        field = "";
        value = "";
    }

    /**
     *
     * @param field - field name
     * @param value - field value
     */
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

    /**
     *
     * @return string in file format: field = value
     */
    @Override
    public String toFile() {
        return field + delimiter() + value;
    }

    /**
     *
     * @return name of the field
     */
    @Override
    public String name() {
        return field;
    }

    /**
     *
     * @return field value
     */
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
