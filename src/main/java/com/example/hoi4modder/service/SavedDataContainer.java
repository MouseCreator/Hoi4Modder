package com.example.hoi4modder.service;

public class SavedDataContainer {
    private static SavedData savedData;

    private SavedDataContainer() {}
    public static SavedData get() {
        if (savedData == null) {
           savedData = SavedDataFactory.factory().getSavedData();
        }
        return savedData;
    }
}
