package com.example.hoi4modder.model.files.service;

public interface FileService {
    void put(Model model, String path, String targetBlock);
    void add(Model model, String path, String targetBlock);
}
