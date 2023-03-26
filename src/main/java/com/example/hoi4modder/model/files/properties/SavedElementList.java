package com.example.hoi4modder.model.files.properties;

import java.util.List;

public class SavedElementList implements SavedList{
    private final SavedListArray array;
    public SavedElementList() {
        array = new SavedListArray();
    }
    public SavedElementList(SavedListArray array) {
        this.array = array;
    }

    @Override
    public String prefix() {
        return "";
    }

    @Override
    public String suffix() {
        return "";
    }

    @Override
    public List<SavedElement> getElements() {
        return array.getElements();
    }

    @Override
    public String toFile() {
        StringBuilder builder = new StringBuilder();
        for (SavedElement element : array) {
            builder.append(element.toFile()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public void add(SavedElement element) {
        this.array.add(element);
    }

    public SavedListArray getArray() {
        return array;
    }
}
