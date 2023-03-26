package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

abstract class AbstractFactory implements ChainedFactory{

    public abstract ChainedFactory next();

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
        return getProperty().containsDelimiter(origin);
    }

    public abstract Property toProperty(String origin);

    protected abstract Property getProperty();
}


