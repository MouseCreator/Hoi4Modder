package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;


public interface ChainedFactory {
    Property createProperty(String origin);
    boolean canHandle(String string);
}
