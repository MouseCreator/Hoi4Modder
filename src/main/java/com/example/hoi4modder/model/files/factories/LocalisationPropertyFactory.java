package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

class LocalisationPropertyFactory extends AbstractFactory{

    private final LocalisationProperty property = new LocalisationProperty();

    private final SingleValueFactory next = new SingleValueFactory();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        String key;
        String value;
        int splitPosition = origin.indexOf(":");
        key = origin.substring(0, splitPosition);
        value = origin.substring(splitPosition+1);
        return new LocalisationProperty(key, value);
    }

    @Override
    protected Property getProperty() {
        return property;
    }

    @Override
    public boolean canHandle(String string) {
        return string.contains(property.delimiter());
    }
}
