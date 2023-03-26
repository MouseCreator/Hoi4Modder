package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.LocalisationProperty;
import com.example.hoi4modder.model.files.Property;
import com.example.hoi4modder.model.files.SingleValue;

public class SingleValueFactory extends AbstractFactory {
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
