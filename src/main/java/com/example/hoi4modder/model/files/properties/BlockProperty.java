package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyList;
import com.example.hoi4modder.model.files.properties.styles.PrintStyle;

public class BlockProperty implements Property {
    private String key = "";

    private PrintStyle style;
    private final PropertyList block;

    public BlockProperty(String name, PropertyList expressions) {
        this.key = name;
        this.block = expressions;
    }

    public BlockProperty() {
        this.key = "";
        this.block = new PropertyList();
    }

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

    public String prefix() {
        return key + " = {";
    }

    public String suffix() {
        return "}";
    }

    public PropertyList getElements() {
        return block;
    }

    @Override
    public String toFile() {
        return prefix() + block.toFile() + suffix();
    }

    @Override
    public String name() {
        return this.key;
    }

    @Override
    public String value() {
        return block.toString();
    }

    @Override
    public void add(Property element) {
        block.add(element);
    }

    @Override
    public void put(Property element) {
        block.add(element);
    }

    public PrintStyle getStyle() {
        return style;
    }

    public void setStyle(PrintStyle style) {
        this.style = style;
    }
}
