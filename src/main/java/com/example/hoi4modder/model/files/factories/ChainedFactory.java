package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.Property;

public interface ChainedFactory {
    Property createProperty(String origin);
    boolean canHandle();
}
