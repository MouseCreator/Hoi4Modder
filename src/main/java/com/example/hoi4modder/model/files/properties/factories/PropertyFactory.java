package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

import java.util.Queue;

public interface PropertyFactory {
    Property toProperty(String origin);

}
