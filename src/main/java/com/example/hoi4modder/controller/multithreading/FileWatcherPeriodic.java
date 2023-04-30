package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterListEditor;
import javafx.application.Platform;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWatcherPeriodic implements FileWatcher {
    ScheduledExecutorService executor;
    private long lastUpdated;
    private String filePath;
    private final CharacterListEditor editor;
    Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File(filePath);
            if (editor.getParent().getWindow().isFocused() && file.lastModified() != lastUpdated) {

                lastUpdated = file.lastModified();
                Platform.runLater(editor::onFileExternalUpdate);
            }
        }
    };
    public FileWatcherPeriodic(CharacterListEditor editor) {
        this.editor = editor;
    }
    public void start() {
        executor = Executors.newScheduledThreadPool(1, r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
        executor.scheduleAtFixedRate(checkRunnable, 0, 500, TimeUnit.MILLISECONDS);
    }
    public void stop() {
        executor.close();
    }

    @Override
    public void setFile(String filename) {
        this.filePath = filename;

        this.lastUpdated = new File(filePath).lastModified();
    }
}
