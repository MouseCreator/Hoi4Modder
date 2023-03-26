package com.example.hoi4modder.model.files.factories;


import com.example.hoi4modder.model.files.properties.*;

import java.util.Queue;

public class SavedElementFactory extends Factory {

    private static final SavedElementFactory factory = new SavedElementFactory();

    private final PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();

    public SavedElementFactory getFactory() {
        return factory;
    }
    public SavedElement fromDefaultFile(Queue<String> lines) {
        return new SavedElementList(constructList(lines));
    }
    private SavedListArray constructList(Queue<String> lines) {
        SavedListArray list = new SavedListArray();
        while (!lines.isEmpty()) {
            String current = lines.poll();
            if (isBlockList(current)) {
                BlockListProperty toAdd = new BlockListProperty();
                toAdd.setKey(current.split("=", 2)[0]);
                toAdd.setBlock(constructList(lines));
            } else {
                list.add(propertyFactory.toProperty(current));
            }
        }
        return list;
    }

    private boolean isBlockList(String current) {
        return current.contains("=") && current.contains("{");
    }

    @Override
    public void createSavedElement(Queue<String> source) {

    }
}
