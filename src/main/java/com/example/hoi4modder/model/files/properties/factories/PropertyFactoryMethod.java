package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

abstract class PropertyFactoryMethod implements ChainedFactory{

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
        return getProperty().containsDelimiter(origin.split(" ")[0]);
    }

    public abstract Property toProperty(String origin);

    protected abstract Property getProperty();
}


