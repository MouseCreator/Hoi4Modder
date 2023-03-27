package com.example.hoi4modder.model.files.properties;

public interface Property {
    String delimiter();
    boolean containsDelimiter(String str);
    String toFile();
}
