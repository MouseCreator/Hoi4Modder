package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

import java.util.Queue;

public interface ChainedFactory {
    Property createProperty(String origin);
    boolean canHandle(String string);
}
