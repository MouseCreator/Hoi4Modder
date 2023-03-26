package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.printstyles.PrintStyle;

import java.util.List;

public class BlockListProperty implements Property, SavedList {
    private String key = "";

    private SavedListArray block = new SavedListArray();

    private PrintStyle printStyle;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
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

    @Override
    public List<SavedElement> getElements() {
        return block.getElements();
    }

    @Override
    public String toFile() {
        return prefix() + printStyle.printStyled(this) + suffix();
    }

    @Override
    public void add(SavedElement element) {
        block.add(element);
    }

    public SavedListArray getBlock() {
        return block;
    }

    public void setBlock(SavedListArray block) {
        this.block = block;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
