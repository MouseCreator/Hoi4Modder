package com.example.hoi4modder.model.files.factories;

import java.util.Queue;

public abstract class Factory {
    public abstract void createSavedElement(Queue<String> source);
}
