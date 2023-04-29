package com.example.hoi4modder.controller.multithreading;

import javafx.concurrent.Task;

import java.io.File;

public class FileWatcher extends Task<Void> {
    private long lastUpdate;

    private File file;


    @Override
    protected Void call() throws Exception {
        if (lastUpdate != file.lastModified())
            return null;
        return null;
    }
}
