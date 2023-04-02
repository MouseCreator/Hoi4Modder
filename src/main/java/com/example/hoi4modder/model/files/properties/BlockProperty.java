package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import com.example.hoi4modder.model.files.properties.lists.PropertyList;
import com.example.hoi4modder.model.files.properties.styles.NormalStyle;
import com.example.hoi4modder.model.files.properties.styles.PrintRules;
import com.example.hoi4modder.model.files.properties.styles.PrintStyle;

/**
 * Property that consists of FieldValue properties and other Block properties (expressions)
 */
public class BlockProperty implements Block {
    private String key;
    private PrintStyle style;
    private final PropertyList block;

    /**
     * Empty unnamed block property
     */
    public BlockProperty() {
        this.key = "";
        this.block = new PropertyList();
        this.style = new NormalStyle();
    }

    /**
     * Empty named block property
     * @param key - name of the property
     */
    public BlockProperty(String key) {
        this.key = key;
        this.block = new PropertyList();
        this.style = new NormalStyle();
    }

    /**
     *
     * @return name of the block
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets name of the block
     * @param key - new block name
     */
    public void setKey(String key) {
        this.key = key;
        PrintRules rules = new PrintRules();
        rules.forBlock(this);
    }
    @Override
    public String delimiter() {
        return "={";
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

    /**
     *
     * @return all elements of the block
     */
    @Override
    public PropertyList getElements() {
        return block;
    }

    @Override
    public String toFile() {
        return style.styledList(this);
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

    @Override
    public PropertyCollection getAll() {
        return this.block;
    }

    @Override
    public PropertyCollection get(String field) {
        PropertyList result = new PropertyList();
        for (Property property : block) {
            if (property.name().equals(field))
                result.add(property);
        }
        return result;
    }

    @Override
    public Property getFirst(String field) {
        for (Property property : block) {
            if (property.name().equals(field))
                return property;
        }
        return null;
    }

    /**
     *
     * @param style - style to print block property
     */
    public void setStyle(PrintStyle style) {
        this.style = style;
    }
}
