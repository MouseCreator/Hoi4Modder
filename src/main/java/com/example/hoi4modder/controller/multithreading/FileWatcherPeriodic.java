package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterListEditor;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWatcherPeriodic implements FileWatcher {
    ScheduledExecutorService executor;
    private long lastUpdated;
    private File file;
    private final CharacterListEditor editor;
    Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            if (file.lastModified() != lastUpdated) {
                lastUpdated = file.lastModified();
                editor.onFileExternalUpdate();
            }
        }
    };
    public FileWatcherPeriodic(CharacterListEditor editor) {
        this.editor = editor;
    }
    public void setFile(File file) {
        this.file = file;
        this.lastUpdated = file.lastModified();;
    }
    public void start() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(checkRunnable, 0, 5, TimeUnit.SECONDS);
    }
    public void stop() {
        executor.close();
    }

    @Override
    public void setFile(String filename) {
        file = new File(filename);
    }
}
