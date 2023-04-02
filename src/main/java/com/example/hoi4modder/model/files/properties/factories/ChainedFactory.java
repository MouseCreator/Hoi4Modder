package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

/**
 * CHAIN OF RESPONSIBILITY
 */
public interface ChainedFactory {
    Property createProperty(String origin);
    boolean canHandle(String string);
    ChainedFactory next();
}
