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
        String[] strings = split(origin);
        String title = origin.split(" ", 2)[0];
        title = title.substring(0, title.length()-1);
        LocalisationBlock block = new LocalisationBlock(title);
        for (String current : strings) {
            block.add(propertyFactory.toProperty(current));
        }
        return block;
    }
    @Override
    protected String[] split(String str) {
        String[] inSplit = super.split(str);
        return inSplit[1].split("\" ");
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
