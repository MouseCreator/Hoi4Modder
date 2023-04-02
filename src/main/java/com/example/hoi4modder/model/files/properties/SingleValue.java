package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

/**
 * Property that is represented by a single word or number.
 * May be separated with others with space or newline
 */
public class SingleValue implements Property{
    private final String value;

    /**
     *
     * @param origin - word or number to put in property
     */
    public SingleValue(String origin) {
        this.value = origin;
    }

    /**
     * Creates empty property
     */
    public SingleValue() {
        this.value = "";
    }

    /**
     *
     * @return delimiter of single value - empty string, since it contains only value
     */
    @Override
    public String delimiter() {
        return "";
    }

    /**
     *
     * @param str - string from formatted file
     * @return always true, since any string may be considered as set of words
     */
    @Override
    public boolean containsDelimiter(String str) {
        return true;
    }

    /**
     *
     * @return value of the property
     */
    @Override
    public String toFile() {
        return value;
    }

    /**
     *
     * @return empty string, since single value contains unnamed value
     */
    @Override
    public String name() {
        return "";
    }

    /**
     *
     * @return value of the property
     */
    @Override
    public String value() {
        return value;
    }

    @Override
    public void add(Property other) {
        throw new UnsupportedOperationException("Cannot add values to single value type");
    }

    @Override
    public void put(Property other) {
        throw new UnsupportedOperationException("Cannot add values to single value type");
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
        return  (field.isEmpty()) ? this : null;
    }


}
