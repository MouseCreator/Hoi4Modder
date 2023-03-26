package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

public class PropertyFactoryImpl implements PropertyFactory{
    private final BlockPropertyFactory firstInChain = new BlockPropertyFactory();
    @Override
    public Property toProperty(String origin) {
        return firstInChain.createProperty(origin);
    }

}
