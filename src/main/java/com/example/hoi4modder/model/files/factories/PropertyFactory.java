package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class PropertyFactory{
    private final PropertyFactory factory = new PropertyFactory();
    public PropertyFactory getFactory() {
        return factory;
    }
    private BlockPropertyFactory firstInChain;

    public Property toProperty(String origin) {
        return firstInChain.createProperty(origin);
    }

}
