package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.FieldValueProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class FieldValuePropertyFactory extends PropertyFactoryMethod {

    private final FieldValueProperty property = new FieldValueProperty();

    private final DoublePropertyFactory next = new DoublePropertyFactory();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        String[] s = origin.split("=", 2);
        String field = s[0].trim();
        String value = s[1].trim();
        return new FieldValueProperty(field, value);
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
