package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SavedElement;
import com.example.hoi4modder.model.files.properties.SavedList;

public interface ChainedFactory {
    Property createProperty(String origin);
    boolean canHandle(String string);
}
