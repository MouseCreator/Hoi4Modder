package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.LocalisationBlock;
import com.example.hoi4modder.model.files.properties.Property;


public class LocalisationBlockFactory extends PropertyFactoryMethod{



    private final SingleValueFactory next = new SingleValueFactory();

    private final LocalisationBlock property = new LocalisationBlock();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
        String[] titleAndKeys = origin.split(": ", 2);
        String title = titleAndKeys [0];
        String[] strings = splitAll(titleAndKeys[1]);
        LocalisationBlock block = new LocalisationBlock(title);
        for (String current : strings) {
            block.add(propertyFactory.toProperty(current));
        }
        return block;
    }
    private String[] splitAll(String str) {
        String[] inSplit = str.split("\" ");
        for (int i = 0; i < inSplit.length; i++) {
            inSplit[i] += "\"";
        }
        return inSplit;
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
