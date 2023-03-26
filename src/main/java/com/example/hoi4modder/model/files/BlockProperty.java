package com.example.hoi4modder.model.files;
public class BlockProperty implements Property{
    private String value;
    private SavedList block;
    @Override
    public String delimiter() {
        return "= {";
    }

    public String closedDelimiter() {
        return "}";
    }

    @Override
    public String toFile() {
        return value + delimiter() + block.toFile() +closedDelimiter();
    }

}
