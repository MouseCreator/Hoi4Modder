package com.example.hoi4modder.model.files.properties;

public interface SavedElement {
    String toFile();

    void inject(Object baseObject);
}
