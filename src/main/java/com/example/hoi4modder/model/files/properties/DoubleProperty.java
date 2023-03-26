package com.example.hoi4modder.model.files.properties;

public class DoubleProperty implements Property{
    private String property;
    private String value;
    @Override
    public String delimiter() {
        return " ";
    }

    @Override
    public String toFile() {
        return property + " " + value;
    }

}
