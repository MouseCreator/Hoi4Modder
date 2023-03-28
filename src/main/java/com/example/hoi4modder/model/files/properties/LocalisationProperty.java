package com.example.hoi4modder.model.files.properties;

public class LocalisationProperty implements Property{
    private final String key;
    private final String value;

    public LocalisationProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public LocalisationProperty() {
        this.key = "";
        this.value = "";
    }

    @Override
    public String delimiter() {
        return ":0";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.contains(":0") || str.contains(":1") || str.contains(":2");
    }

    @Override
    public String toFile() {
        return key + delimiter() + " \"" + value + "\"";
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

}
