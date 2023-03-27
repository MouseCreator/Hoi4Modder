package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyList;

public class LocalisationBlock implements Property{
    private String language;

    private PropertyList keys;
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
}
