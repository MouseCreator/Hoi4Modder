package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.FieldValueProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class DoublePropertyFactory extends PropertyFactoryMethod {

    private final LocalisationPropertyFactory next = new LocalisationPropertyFactory();

    private final FieldValueProperty property = new FieldValueProperty();
    @Override
    public ChainedFactory next() {
        return next;
    }

    @Override
    public Property toProperty(String origin) {
        String[] strings = origin.split(" ");
        String field = null;
        String value = null;
        for (String s : strings) {
            if (s != null && s.isEmpty())
                continue;
            if (field == null)
                field = s;
            else
                value = s;
        }
        return new FieldValueProperty(field, value);
    }

    @Override
    protected Property getProperty() {
        return property;
    }
}
