package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

public interface Block extends Property{
    PropertyCollection getElements();

    String prefix();

    String suffix();
}
