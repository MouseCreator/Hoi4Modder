package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.LocalisationBlock;
import com.example.hoi4modder.model.files.properties.LocalisationProperty;
import com.example.hoi4modder.model.files.properties.Property;

import java.util.Queue;

public class LocalisationBlockFactory extends PropertyFactoryMethod{

    private final FieldValuePropertyFactory next = new FieldValuePropertyFactory();

    private final LocalisationBlock property = new LocalisationBlock();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        String[] strings = origin.split(" ", 2);
        LocalisationBlock block = new LocalisationBlock(strings[0]);
        PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
        while (!origin.isEmpty())
            block.add(propertyFactory.toProperty(origin));
        return block;
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
