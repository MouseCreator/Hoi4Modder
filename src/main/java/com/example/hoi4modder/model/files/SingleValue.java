package com.example.hoi4modder.model.files;

public class SingleValue implements Property{
    private final String value;

    public SingleValue(String origin) {
        this.value = origin;
    }

    @Override
    public String delimiter() {
        return "";
    }

    @Override
    public String toFile() {
        return value;
    }
}
