package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterListEditor;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWatcherPeriodic implements FileWatcher {
    final ScheduledExecutorService executor;

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
    public FileWatcherPeriodic(File file, CharacterListEditor editor) {
        executor = Executors.newScheduledThreadPool(1);
        this.editor = editor;
        this.file = file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public void start() {
        executor.scheduleAtFixedRate(checkRunnable, 0, 5, TimeUnit.SECONDS);
    }
    public void stop() {
        executor.close();
    }
}
