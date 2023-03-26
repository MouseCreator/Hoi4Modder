package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SingleValue;

public class BlockPropertyFactory extends AbstractFactory {
    private BlockProperty property = new BlockProperty();
    private LocalisationPropertyFactory nextInChain;

    @Override
    public ChainedFactory next() {
        return nextInChain;
    }

    @Override
    public Property toProperty(String origin) {
        return new SingleValue(origin);
    }
}
