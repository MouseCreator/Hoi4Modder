package com.example.hoi4modder.model.files.properties.lists;

import com.example.hoi4modder.model.files.properties.Property;

/**
 * Collection of the properties
 */
public interface PropertyCollection extends Iterable<Property> {
    /**
     *
     * @param index - number of property
     * @return property at specified index
     */
    Property get(int index);

    /**
     * Adds property to collection
     * @param property - property to add
     */
    void add(Property property);

    /**
     * Removes property from collection
     * @param property - property to remove
     */
    void pop(Property property);

    /**
     * Removes all elements from collection
     */
    void clear();

    /**
     *
     * @return size of the collection
     */
    int size();

    /**
     *
     * @return formatted string representation of the list
     */
    String toFile();
}
