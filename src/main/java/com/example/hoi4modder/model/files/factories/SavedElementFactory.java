package com.example.hoi4modder.model.files.factories;


import com.example.hoi4modder.model.files.properties.*;
import com.example.hoi4modder.model.files.properties.printstyles.PrintStyle;

import java.util.Queue;

public class SavedElementFactory extends Factory {
    PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
    public static SavedElement fromFile(Queue<String> stringSet) {
        return new SavedElementList(constructList(stringSet));
    }
    private static SavedListArray constructList(Queue<String> stringSet) {
        SavedListArray list = new SavedListArray();
        while (!stringSet.isEmpty()) {
            String current = stringSet.poll();
            if (isBlockList(current)) {
                BlockListProperty toAdd = new BlockListProperty();
                toAdd.setKey(current.split("=", 2)[0]);
                toAdd.setBlock(constructList(stringSet));
            }
        }
        return list;
    }

    private static boolean isBlockList(String current) {
        return current.contains("=") && current.contains("{");
    }

    @Override
    public void createSavedElement(Queue<String> source) {

    }
}
