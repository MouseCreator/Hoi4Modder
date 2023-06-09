package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

/**
 * Localisation property consists of key and value.
 * Keys are unique, each key has one value
 */
public class LocalisationProperty implements Property{
    private final String key;
    private final String value;
    private final int version;

    /**
     *
     * @param key - key of the localisation
     * @param value - localisation value
     */
    public LocalisationProperty(String key, String value) {
        this.key = key;
        this.value = value;
        this.version = 0;
    }

    /**
     * Empty localisation property
     */
    public LocalisationProperty() {
        this.key = "";
        this.value = "";
        this.version = 0;
    }

    /**
     *
     * @param key - unique key for localisation
     * @param value - localisation value
     * @param version - version of the localisation. Doesn't affect program, stored for developers.
     */
    public LocalisationProperty(String key, String value, int version) {
        this.key = key;
        this.value = value;
        this.version = version;
    }

    @Override
    public String delimiter() {
        return ":0";
    }

    /**
     *
     * @param str - string from formatted file
     * @return true, if string fits format "key:version value"
     */
    @Override
    public boolean containsDelimiter(String str) {
        return str.matches("[a-zA-Z0-9-_]+:[0-9]+(.+)");
    }

    /**
     *
     * @return line of localisation for yml file
     */
    @Override
    public String toFile() {
        return key + ":" + version + " " + value;
    }

    /**
     *
     * @return localisation key
     */
    @Override
    public String name() {
        return key;
    }

    /**
     *
     * @return localisation value
     */
    @Override
    public String value() {
        return value;
    }

    @Override
    public void add(Property other) {
        throw new UnsupportedOperationException("Cannot add values to localisation key");
    }

    @Override
    public void put(Property other) {
        throw new UnsupportedOperationException("Cannot add values to localisation key");
    }

    @Override
    public PropertyCollection getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PropertyCollection get(String field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Property getFirst(String field) {
        return this.key.equals(field) ? this : null;
    }

}
