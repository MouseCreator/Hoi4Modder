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
        String[] versionValue = value.split("\"", 2);
        int version = Integer.parseInt(versionValue[0]);
        value = "\"" + versionValue[1];
        return new LocalisationProperty(key, value, version);
    }

    @Override
    protected Property getProperty() {
        return property;
    }
    
}
