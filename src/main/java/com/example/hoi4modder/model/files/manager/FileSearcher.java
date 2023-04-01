package com.example.hoi4modder.model.files.manager;

import java.io.File;
import java.util.NoSuchElementException;

public class FileSearcher {

    private String directory;
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }



    public FileSearcher(String directory) {
        this.directory = directory;
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

    public String findAppendedFile(String keyword) {
        File[] files = getFilesFromDirectory();
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                continue;
            }
            String name = fileEntry.getName();
            if (name.contains(keyword))
                return fileEntry.getPath();
        }
        throw new NoSuchElementException("Cannot find file containing " + keyword + " at " + directory);
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
