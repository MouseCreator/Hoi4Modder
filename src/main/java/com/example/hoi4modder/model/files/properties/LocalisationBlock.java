package com.example.hoi4modder.model.files.properties;
import com.example.hoi4modder.model.files.properties.lists.PropertyList;

public class LocalisationBlock implements Property{
    private String language;

    private PropertyList keys = new PropertyList();
    @Override
    public String delimiter() {
        return ":";
    }
    @Override
    public boolean containsDelimiter(String str) {
        return str.matches("l_[A-Za-z0-9]*:");
    }
    @Override
    public String toFile() {
        return language + delimiter();
    }
    @Override
    public String name() {
        return language;
    }

    @Override
    public String value() {
        return keys.toString();
    }

    @Override
    public void add(Property other) {

    }

    @Override
    public void put(Property other) {

    }
}
