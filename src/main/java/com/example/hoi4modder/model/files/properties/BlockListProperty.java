package com.example.hoi4modder.model.files.properties;

public class BlockListProperty extends SavedListArray implements Property {
    private final String key = "";
    @Override
    public String delimiter() {
        return "= {";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.contains("=") && str.contains("{");
    }

    @Override
    public String prefix() {
        return key + " = {";
    }

    @Override
    public String suffix() {
        return "}";
    }

}
