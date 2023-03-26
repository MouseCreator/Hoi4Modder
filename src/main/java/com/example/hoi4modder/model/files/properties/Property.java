package com.example.hoi4modder.model.files.properties;

public interface Property extends SavedElement {
    String delimiter();
    boolean containsDelimiter(String str);
}
