package com.example.hoi4modder.model.files.properties;

public class FieldValueProperty implements Property {
    private String value;
    private String field;
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
}
