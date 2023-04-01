package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

public class LocalisationProperty implements Property{
    private final String key;
    private final String value;
    private final int version;

    public LocalisationProperty(String key, String value) {
        this.key = key;
        this.value = value;
        this.version = 0;
    }

    public LocalisationProperty() {
        this.key = "";
        this.value = "";
        this.version = 0;
    }

    public LocalisationProperty(String key, String value, int version) {
        this.key = key;
        this.value = value;
        this.version = version;
    }

    @Override
    public String delimiter() {
        return ":0";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.matches("[a-zA-Z0-9-_]+:[0-9]+(.+)");
    }

    @Override
    public String toFile() {
        return key + ":" + version + value;
    }

    @Override
    public String name() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public void add(Property other) {
        throw new UnsupportedOperationException("Cannot add values to localisation key");
    }

    @Override
    public void put(Property other) {
        throw new UnsupportedOperationException("Cannot add values to localisation key");
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
        return this.key.equals(field) ? this : null;
    }

}
