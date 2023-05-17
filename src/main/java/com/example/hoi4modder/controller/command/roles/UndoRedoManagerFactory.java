package com.example.hoi4modder.controller.command.roles;


import java.util.HashMap;
import java.util.TreeMap;

public class UndoRedoManagerFactory {

    private UndoRedoManagerFactory(){}
    private static UndoRedoManagerFactory factory;
    public static UndoRedoManagerFactory get() {
        if (factory == null) {
            factory = new UndoRedoManagerFactory();
        }
        return factory;
    }
    public UndoRedoManager treeMap() {
        return new UndoRedoManagerMap(new TreeMap<>());
    }
    public UndoRedoManager hashMap() {
        return new UndoRedoManagerMap(new HashMap<>());
    }
}
