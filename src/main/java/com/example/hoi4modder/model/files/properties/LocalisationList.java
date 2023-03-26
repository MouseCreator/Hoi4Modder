package com.example.hoi4modder.model.files.properties;

import java.util.List;

public class LocalisationList implements SavedList {
    private String localisationKey;

    @Override
    public String prefix() {
        return localisationKey + ":";
    }

    @Override
    public String suffix() {
        return "\n";
    }

    @Override
    public List<SavedElement> getElements() {
        return null;
    }

    @Override
    public String toFile() {
        return prefix();
    }

    @Override
    public void add(SavedElement property) {

    }
}
