package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

import java.util.Queue;

class SingleValueFactory extends PropertyFactoryMethod {
    private final SingleValue property = new SingleValue();

    @Override
    public ChainedFactory next() {
        throw new UnsupportedOperationException("Cannot get next in chain for SingleValue property.");
    }

    @Override
    public Property toProperty(String origin) {
        return new SingleValue(origin);
    }

    @Override
    protected Property getProperty() {
        return property;
    }

    @Override
    public boolean canHandle(String string) {
        return true;
    }

}
