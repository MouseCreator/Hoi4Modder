package com.example.hoi4modder.model.files.manager;
import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;

import java.io.File;
import java.util.NoSuchElementException;

public class FileSearcher {

    private String directory;


    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public FileSearcher(String directory) {
        this.directory = directory;
    }
    public FileSearcher() {
    }

    public String findCountryByTag(String tag) {
        File[] files = getFilesFromDirectory();
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                continue;
            }
            String name = fileEntry.getName();
            if (name.startsWith(tag))
                return fileEntry.getPath();
        }
        throw new NoSuchElementException("Cannot find country with tag " + tag + " at " + directory);
    }

    private File[] getFilesFromDirectory() {
        File baseDir = new File(directory);
        File[] files = baseDir.listFiles();
        if (files == null) {
            throw new RuntimeException("Cannot get files in" + directory);
        }
        return files;
    }
    public String findExactFile(String filename) {
        File[] files = getFilesFromDirectory();
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                continue;
            }
            String name = fileEntry.getName();
            if (name.equals(filename))
                return fileEntry.getPath();
        }
        throw new NoSuchElementException("Cannot find file named " + filename + " at " + directory);
    }
}
