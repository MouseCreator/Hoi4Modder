package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.Property;

class LocalisationPropertyFactory extends AbstractFactory{
    @Override
    public ChainedFactory next() {
        return null;
    }

    @Override
    public Property toProperty(String origin) {
        return null;
    }

    @Override
    public boolean canHandle() {
        return false;
    }
}
