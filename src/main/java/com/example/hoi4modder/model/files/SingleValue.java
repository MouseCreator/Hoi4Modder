package com.example.hoi4modder.model.files;

public class SingleValue implements Property{
    private String value;
    @Override
    public String delimiter() {
        return "";
    }

    @Override
    public String toFile() {
        return value;
    }
}
