package com.example.hoi4modder.model.files.properties.lists;

import com.example.hoi4modder.model.files.properties.Property;

public interface PropertyCollection extends Iterable<Property> {
    Property get(int index);
    void add(Property property);
    void pop(Property property);

    void clear();
    int size();
}
