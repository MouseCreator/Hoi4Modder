package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

public interface Property {
    String delimiter();
    boolean containsDelimiter(String str);
    String toFile();
    String name();
    String value();
    void add(Property other);
    void put(Property other);

    PropertyCollection getAll();
    PropertyCollection get(String field);

    Property getFirst(String field);
}
