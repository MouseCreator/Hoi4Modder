package com.example.hoi4modder.controller.multithreading;

/**
 * Interface of class that monitors file changes
 */
public interface FileWatcher {
    /**
     * starts to monitor the file
     */
    void start();

    /**
     * stops to monitor the file
     */
    void stop();

    /**
     * choose file to be watched
     * @param filename - full name of the file
     */
    void setFile(String filename);

    /**
     * Adds case, when file modification has to be ignored
     */
    void exception();
}
