package com.example.hoi4modder.model.files.properties;

import java.util.List;

public interface ListElement extends SavedElement {
    String prefix();
    String suffix();
    List<SavedElement> getElements();

}
