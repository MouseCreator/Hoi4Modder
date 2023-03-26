package com.example.hoi4modder.model.files;

public class FieldValueProperty implements Property {
    private String value;
    private String field;
    @Override
    public String delimiter() {
        return " = ";
    }

    @Override
    public String toFile() {
        return field + delimiter() + value;
    }
}
