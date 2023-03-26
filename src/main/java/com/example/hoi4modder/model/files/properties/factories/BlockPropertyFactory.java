package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.BlockListProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

public class BlockPropertyFactory extends PropertyFactoryMethod {
    private final BlockListProperty property = new BlockListProperty();
    private final FieldValuePropertyFactory nextInChain = new FieldValuePropertyFactory();

    @Override
    public ChainedFactory next() {
        return nextInChain;
    }

    @Override
    public Property toProperty(String origin) {
        return new SingleValue(origin);
    }
    @Override
    protected Property getProperty() {
        return property;
    }
}
