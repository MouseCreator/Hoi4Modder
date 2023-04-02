package com.example.hoi4modder.model.files.properties.lists;

import com.example.hoi4modder.model.files.properties.Property;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Property collection based on array list
 */
public class PropertyList implements PropertyCollection {

    private final ArrayList<Property> properties = new ArrayList<>();

    @Override
    public Property get(int index) {
        if (index >= properties.size()) {
            throw new IndexOutOfBoundsException("Cannot get index " + index + " in collection of size " + properties.size());
        }
        return properties.get(index);
    }
    @Override
    public void add(Property property) {
        properties.add(property);
    }
    @Override
    public void pop(Property property) {
        properties.remove(property);
    }

    @Override
    public void clear() {
        this.properties.clear();
    }

    @Override
    public int size() {
        return properties.size();
    }
    @Override
    public String toFile() {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append(property.toFile());
        }
        return builder.toString();
    }

    /**
     *
     * @return iterator of the property list
     */
    @Override
    public Iterator<Property> iterator() {
        return properties.iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append(property.toFile());
        }
        return builder.toString();
    }
}
