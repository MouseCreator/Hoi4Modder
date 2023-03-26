package com.example.hoi4modder.model.files.properties;

public class DoubleProperty implements Property{
    private String property;
    private String value;
    @Override
    public String delimiter() {
        return " ";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.matches(" *[a-zA-Z0-9_]+ [a-zA-Z0-9_]+ *");
    }

    @Override
    public String toFile() {
        return property + " " + value;
    }

}
