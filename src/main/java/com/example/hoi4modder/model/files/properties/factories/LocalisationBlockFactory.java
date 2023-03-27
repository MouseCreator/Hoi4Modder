package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.LocalisationBlock;
import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class LocalisationBlockFactory extends PropertyFactoryMethod{

    private final FieldValuePropertyFactory next = new FieldValuePropertyFactory();

    private final LocalisationBlock property = new LocalisationBlock();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        return new LocalisationProperty();
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
