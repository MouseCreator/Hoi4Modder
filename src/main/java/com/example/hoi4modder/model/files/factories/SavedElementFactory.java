package com.example.hoi4modder.model.files.factories;


import com.example.hoi4modder.model.files.properties.ListElement;
import com.example.hoi4modder.model.files.properties.SavedListArray;

import java.util.Queue;

public class SavedElementFactory extends Factory {
    PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();

    public ListElement constructList(Queue<String> stringSet) {
        SavedListArray list = new SavedListArray();
        while (!stringSet.isEmpty()) {
            String current = stringSet.poll();

        }
        return list;
    }

    @Override
    public void createSavedElement(Queue<String> source) {

    }
}
