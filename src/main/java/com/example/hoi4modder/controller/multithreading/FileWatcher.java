package com.example.hoi4modder.controller.multithreading;

public interface FileWatcher {
    void start();
    void stop();
    void setFile(String filename);

    void exception();
}
