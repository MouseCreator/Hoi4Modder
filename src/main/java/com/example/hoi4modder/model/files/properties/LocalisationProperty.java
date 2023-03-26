package com.example.hoi4modder.model.files.properties;

public class LocalisationProperty implements Property{
    private String key;
    private String value;
    @Override
    public String delimiter() {
        return ":";
    }

    @Override
    public String toFile() {
        return key + delimiter() + "0\"" + value + "\"";
    }
}
