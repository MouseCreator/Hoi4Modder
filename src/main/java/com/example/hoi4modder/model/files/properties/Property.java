package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

/**
 * COMPOSITE PATTERN
 */
public interface Property {
    /**
     *
     * @return string that separates name and value of the property
     */
    String delimiter();

    /**
     *
     * @param str - string from formatted file
     * @return true, if string fits property signature
     */
    boolean containsDelimiter(String str);

    /**
     * Turns property to string in format for file
     * @return string in format for file
     */
    String toFile();

    /**
     * @return name of the property
     */
    String name();

    /**
     *
     * @return value of the property
     */
    String value();

    /**
     * Adds property if this property is a block
     * @param other - property to add in the block
     * @throws UnsupportedOperationException if property is simple
     */
    void add(Property other) throws UnsupportedOperationException;

    /**
     * Adds property to block if the block doesn't contain it
     * @param other - property to put
     * @throws UnsupportedOperationException if property is simple
     */
    void put(Property other) throws UnsupportedOperationException;

    /**
     *
     * @return list of all child properties of the block
     * @throws UnsupportedOperationException if property is simple
     */
    PropertyCollection getAll() throws UnsupportedOperationException;

    /**
     * Gets properties by name. Method is used to get properties that may have multiple occurrences
     * @param field - name of target property
     * @return list of all properties with name {@param field}
     */
    PropertyCollection get(String field);

    /**
     * Gets one of the properties by name. Method is used to get properties that usually have only one occurrence
     * @param field - name of target property
     * @return first instance of the property named {@param field}
     */
    Property getFirst(String field);
}
