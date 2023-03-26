package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.printstyles.PrintStyle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SavedListArray implements SavedList, Iterable<SavedElement> {
    protected final ArrayList<SavedElement> savedElements = new ArrayList<>();

    protected PrintStyle printStyle;

    @Override
    public String toFile() {
        return printStyle.printStyled(this);
    }

    @Override
    public void add(SavedElement element) {
        savedElements.add(element);
    }

    @Override
    public Iterator<SavedElement> iterator() {
       return savedElements.iterator();
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
        return savedElements;
    }
}
