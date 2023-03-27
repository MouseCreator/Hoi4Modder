package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.transform.FieldInjection;

public class SingleValue implements Property{
    private final String value;

    public SingleValue(String origin) {
        this.value = origin;
    }
    public SingleValue() {
        this.value = "";
    }

    @Override
    public String delimiter() {
        return "";
    }

    @Override
    public boolean containsDelimiter(String str) {
        return true;
    }

    @Override
    public String toFile() {
        return value;
    }

    @Override
    public void inject(Object baseObject) {
       // FieldInjection.getInjector().constructor(baseObject, this.value);
    }


}
