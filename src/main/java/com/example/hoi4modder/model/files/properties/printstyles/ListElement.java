package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SavedElement;

import java.util.List;

public interface ListElement extends SavedElement {
    String prefix();
    String suffix();

    List<SavedElement> getElements();
}
