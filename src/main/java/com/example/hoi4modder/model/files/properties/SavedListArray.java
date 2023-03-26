package com.example.hoi4modder.model.files.properties;

import java.util.ArrayList;

public class SavedListArray implements SavedList{
    private final ArrayList<Property> properties = new ArrayList<>();

    @Override
    public String toFile() {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append(property.toFile());
        }
        return builder.toString();
    }
}
