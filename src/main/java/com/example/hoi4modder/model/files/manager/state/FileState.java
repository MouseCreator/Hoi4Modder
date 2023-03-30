package com.example.hoi4modder.model.files.manager.state;

public interface FileState {
    void write(String message);

    String read();
}
