package com.example.hoi4modder.controller.multithreading;

import javafx.application.Platform;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWatcherPeriodic implements FileWatcher {
    ScheduledExecutorService executor;
    private long lastUpdated;
    private String filePath;
    private final Runnable onFileChange;
    Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File(filePath);

            if (file.lastModified() != lastUpdated) {
                lastUpdated = file.lastModified();
                Platform.runLater(onFileChange); //callback
            }
        }
    };
    public FileWatcherPeriodic(Runnable onFileChange) {
        this.onFileChange = onFileChange;
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
    //editor.getParent().getWindow().isFocused() &&
}
