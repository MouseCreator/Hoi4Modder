package com.example.hoi4modder.model.files.properties;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import com.example.hoi4modder.model.files.properties.lists.PropertyList;
import com.example.hoi4modder.model.files.properties.styles.LocalisationStyle;
import com.example.hoi4modder.model.files.properties.styles.PrintStyle;

public class LocalisationBlock implements Block{
    private final String language;
    private final PropertyList keys;

    private PrintStyle style = new LocalisationStyle();

    public LocalisationBlock(String language) {
        this.language = language;
        this.keys = new PropertyList();
    }
    public LocalisationBlock() {
        this.language = "";
        this.keys = new PropertyList();
    }
    @Override
    public String delimiter() {
        return ":";
    }
    @Override
    public boolean containsDelimiter(String str) {
        return str.matches("l_[A-Za-z0-9]*:");
    }
    @Override
    public String toFile() {
        return style.styledList(this);
    }
    @Override
    public String name() {
        return language;
    }

    @Override
    public String value() {
        return keys.toString();
    }

    @Override
    public void add(Property other) {

    }

    @Override
    public void put(Property other) {

    }

    @Override
    public PropertyCollection getElements() {
        return keys;
    }

    @Override
    public String prefix() {
        return language + delimiter();
    }

    @Override
    public String suffix() {
        return "\n";
    }

    public PrintStyle getStyle() {
        return style;
    }

    public void setStyle(PrintStyle style) {
        this.style = style;
    }
}
