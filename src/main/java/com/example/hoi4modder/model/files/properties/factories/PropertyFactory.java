package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

/**
 * FACTORY METHOD
 * Creates properties from input string
 */
public interface PropertyFactory {
    Property toProperty(String origin);
}
