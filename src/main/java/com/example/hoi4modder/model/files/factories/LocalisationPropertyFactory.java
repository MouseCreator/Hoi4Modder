package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.Property;

public class LocalisationPropertyFactory extends AbstractFactory{
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
