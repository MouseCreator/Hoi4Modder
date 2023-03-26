package com.example.hoi4modder.model.files.properties;

public class LocalisationList extends SavedListArray {
    private String localisationKey;

    @Override
    public String prefix() {
        return localisationKey + ":";
    }

    @Override
    public String suffix() {
        return "\n";
    }
}
