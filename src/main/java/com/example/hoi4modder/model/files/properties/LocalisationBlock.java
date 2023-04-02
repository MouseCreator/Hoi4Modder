package com.example.hoi4modder.model.files.properties;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import com.example.hoi4modder.model.files.properties.lists.PropertyList;
import com.example.hoi4modder.model.files.properties.styles.LocalisationStyle;
import com.example.hoi4modder.model.files.properties.styles.PrintStyle;

/**
 * Block of localisation.
 * Contains language of localisation and localisation keys
 */
public class LocalisationBlock implements Block{
    private final String language;
    private final PropertyList keys;
    private PrintStyle style = new LocalisationStyle();

    /**
     *
     * @param language - language of localisation
     */
    public LocalisationBlock(String language) {
        this.language = language;
        this.keys = new PropertyList();
    }

    /**
     * Empty localisation block, no language specified
     */
    public LocalisationBlock() {
        this.language = "";
        this.keys = new PropertyList();
    }
    @Override
    public String delimiter() {
        return ":";
    }

    /**
     *
     * @param str - string from formatted file
     * @return true if string is localisation title; considered that localisation title ends with colon
     */
    @Override
    public boolean containsDelimiter(String str) {
        return str.endsWith(":");
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
        this.keys.add(other);
    }

    @Override
    public void put(Property other) {
        this.keys.add(other);
    }

    @Override
    public PropertyCollection getAll() {
        return this.keys;
    }

    @Override
    public PropertyCollection get(String field) {
        PropertyList list = new PropertyList();
        for (Property property : keys) {
            if (property.name().equals(field))
                list.add(property);
        }
        return list;
    }

    /**
     *
     * @param field - key of target property
     * @return localisation value for key
     */
    @Override
    public Property getFirst(String field) {
        for (Property property : keys) {
            if (property.name().equals(field))
                return property;
        }
        return null;
    }

    /**
     *
     * @return keys of localisations
     */
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

    /**
     *
     * @param style - style to print the property
     */
    public void setStyle(PrintStyle style) {
        this.style = style;
    }
}
