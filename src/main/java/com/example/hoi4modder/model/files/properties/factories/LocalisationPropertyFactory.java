package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

import java.util.Queue;

class LocalisationPropertyFactory extends PropertyFactoryMethod {

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
        String str = origin.split(" ")[0];
        int splitPosition = str.indexOf(":");
        key = str.substring(0, splitPosition);
        value = str.substring(splitPosition+1);
        value = value.substring(value.indexOf('\"')+1, value.lastIndexOf("\""));
        return new LocalisationProperty(key, value);
    }

    @Override
    protected Property getProperty() {
        return property;
    }
    
}
