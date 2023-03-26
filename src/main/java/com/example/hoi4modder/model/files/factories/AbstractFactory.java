package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

public abstract class AbstractFactory implements ChainedFactory{

    public abstract ChainedFactory next();

    private Property property;
    @Override
    public Property createProperty(String origin) {
        if (canHandle(origin)) {
            return toProperty(origin);
        } else {
            return next().createProperty(origin);
        }
    }
    @Override
    public boolean canHandle(String origin) {
        return origin.contains(property.delimiter());
    }

    public abstract Property toProperty(String origin);
}


