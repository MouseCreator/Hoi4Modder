package com.example.hoi4modder.controller.multithreading;

import java.io.File;

public class FileWatcher {
    private long lastUpdate;
    private File file;
    public boolean isModified() {
        return lastUpdate != file.lastModified();
    }

    public String getFileName() {
        return file.getName();
    }
}
