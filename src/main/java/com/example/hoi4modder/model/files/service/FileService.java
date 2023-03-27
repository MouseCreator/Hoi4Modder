package com.example.hoi4modder.model.files.service;

import com.example.hoi4modder.model.files.properties.SavedElement;

public interface FileService {
    void put(Model model, String path, String targetBlock);
    void add(Model model, String path, String targetBlock);
    SavedElement read(String path);
}
