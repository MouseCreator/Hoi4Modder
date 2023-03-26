package com.example.hoi4modder.model.files.properties;

public class FieldValueProperty implements Property {
    private final String value;
    private final String field;
    public FieldValueProperty() {
        field = "";
        value = "";
    }
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

    @Override
    public String toFile() {
        return field + delimiter() + value;
    }
}
