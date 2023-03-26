package com.example.hoi4modder.model.files.properties;
public class BlockListProperty implements Property, SavedElement {
    private final String value = "";
    private final SavedList block = new SavedListArray();
    @Override
    public String delimiter() {
        return "= {";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return str.contains("=") && str.contains("{");
    }

    public String closedDelimiter() {
        return "}";
    }

    @Override
    public String toFile() {
        return value + delimiter() + block.toFile() +closedDelimiter();
    }

}
