package com.example.hoi4modder.model.files.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SavedListArray implements Iterable<SavedElement> {
    protected ArrayList<SavedElement> savedElements = new ArrayList<>();

    public void add(SavedElement element) {
        savedElements.add(element);
    }

    @Override
    public Iterator<SavedElement> iterator() {
       return savedElements.iterator();
    }

    public List<SavedElement> getElements() {
        return savedElements;
    }
}
