package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

class SingleValueFactory extends AbstractFactory {
    private SingleValue property;
    private LocalisationPropertyFactory nextInChain;

    @Override
    public ChainedFactory next() {
        return nextInChain;
    }

    @Override
    public Property toProperty(String origin) {
        return new SingleValue(origin);
    }

    @Override
    public boolean canHandle() {
        return false;
    }
}
