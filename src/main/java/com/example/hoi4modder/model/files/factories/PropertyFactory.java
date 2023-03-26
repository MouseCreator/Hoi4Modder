package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.Property;

public class PropertyFactory implements ChainedFactory {
    private PropertyFactory factory = new PropertyFactory();
    public PropertyFactory getFactory() {
        return factory;
    }
    private SingleValueFactory firstInChain;

    @Override
    public Property createProperty(String origin) {
        return firstInChain.createProperty(origin);
    }

    @Override
    public boolean canHandle(String str) {
        return false;
    }
}
