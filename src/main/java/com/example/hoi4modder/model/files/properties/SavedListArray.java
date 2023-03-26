package com.example.hoi4modder.model.files.properties;

import java.util.ArrayList;
import java.util.Iterator;

public class SavedListArray implements SavedList, Iterable<Property> {
    private final ArrayList<Property> properties = new ArrayList<>();

    @Override
    public String toFile() {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append(property.toFile());
        }
        return builder.toString();
    }

    @Override
    public void add(Property property) {
        properties.add(property);
    }

    @Override
    public Iterator<Property> iterator() {
       return properties.iterator();
    }
}
