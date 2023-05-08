package com.example.hoi4modder.controller.multithreading;

import javafx.application.Platform;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWatcherPeriodic implements FileWatcher {
    ScheduledExecutorService executor;
    private long lastUpdated;

    private int exceptionCounter;
    private String filePath;
    private final Runnable onFileChange;
    Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File(filePath);
            boolean runCheck;
            try {
                runCheck = checkCondition.call();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            if (runCheck && file.lastModified() != lastUpdated) {
                lastUpdated = file.lastModified();
                if (exceptionCounter == 0)
                    Platform.runLater(onFileChange); //callback
                else
                    exceptionCounter--;
            }
        }
    };
    Callable<Boolean> checkCondition;

    public FileWatcherPeriodic(Runnable onFileChanged, Callable<Boolean> checkCondition) {
        this.onFileChange = onFileChanged;
        this.checkCondition = checkCondition;
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

    @Override
    public void exception() {
        exceptionCounter++;
    }
}
