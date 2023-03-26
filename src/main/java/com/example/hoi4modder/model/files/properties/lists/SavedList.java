package com.example.hoi4modder.model.files.properties.lists;

import com.example.hoi4modder.model.files.properties.ListElement;
import com.example.hoi4modder.model.files.properties.SavedElement;

public interface SavedList extends ListElement {
    void add(SavedElement element);

}
