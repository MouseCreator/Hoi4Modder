package com.example.hoi4modder.model.files.properties.lists;

import com.example.hoi4modder.model.files.properties.Property;

import java.util.ArrayList;
import java.util.Iterator;

public class PropertyList implements PropertyCollection {

    private final ArrayList<Property> properties = new ArrayList<>();
    public void add(Property property) {
        properties.add(property);
    }
    public void pop(Property property) {
        properties.remove(property);
    }
    public String toFile() {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append(property.toFile());
        }
        return builder.toString();
    }
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
