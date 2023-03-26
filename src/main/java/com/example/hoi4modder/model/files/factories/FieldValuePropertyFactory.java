package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.FieldValueProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class FieldValuePropertyFactory extends AbstractFactory{

    private final FieldValueProperty property = new FieldValueProperty();

    private final DoublePropertyFactory next = new DoublePropertyFactory();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        return null;
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
