package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.Property;

public abstract class AbstractFactory implements ChainedFactory{

    public abstract ChainedFactory next();
    @Override
    public Property createProperty(String origin) {
        if (canHandle()) {
            return toProperty(origin);
        } else {
            return next().createProperty(origin);
        }
    }

    public abstract Property toProperty(String origin);
}
