package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

class LocalisationPropertyFactory extends AbstractFactory{

    private LocalisationProperty property;
    @Override
    public ChainedFactory next() {
        return null;
    }

    @Override
    public Property toProperty(String origin) {
        return null;
    }

    @Override
    public boolean canHandle(String string) {
        return string.contains(property.delimiter());
    }
}
