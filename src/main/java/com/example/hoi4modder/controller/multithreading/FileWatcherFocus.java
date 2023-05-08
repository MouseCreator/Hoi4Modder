package com.example.hoi4modder.controller.multithreading;

import java.io.File;

public class FileWatcherFocus implements FileWatcher {
    private long lastUpdate;

    private int exceptionCounter;
    private File file;
    public FileWatcherFocus(File file) {
        this.file = file;
        lastUpdate = file.lastModified();
    }
    public boolean isModified() {
        return lastUpdate != file.lastModified();
    }

    public String getFileName() {
        return file.getName();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void setFile(String filename) {
        this.file = new File(filename);
    }

    @Override
    public void exception() {
        exceptionCounter++;
    }
}
