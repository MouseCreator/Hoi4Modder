package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

public class BlockPropertyFactory extends AbstractFactory {
    private final BlockProperty property = new BlockProperty();
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
