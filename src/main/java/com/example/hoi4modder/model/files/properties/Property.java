package com.example.hoi4modder.model.files.properties;

public interface Property {
    String delimiter();
    boolean containsDelimiter(String str);
    String toFile();
    String name();
    String value();
    void add(Property other);
    void put(Property other);
}
