package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

class LocalisationPropertyFactory extends PropertyFactoryMethod {

    private final LocalisationProperty property = new LocalisationProperty();
    private final FieldValuePropertyFactory next = new FieldValuePropertyFactory();

    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        String[] strings = origin.split(":", 2);
        String key = strings[0];
        String value = strings[1];
        value = value.substring(value.indexOf('\"')+1, value.lastIndexOf("\""));
        return new LocalisationProperty(key, value);
    }

    @Override
    protected Property getProperty() {
        return property;
    }
    
}
