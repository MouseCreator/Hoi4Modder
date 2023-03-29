package com.example.hoi4modder.model.files.manager;

import java.io.File;
import java.util.NoSuchElementException;

public class FileSearcher {
    public String findCountryByTag(String directory, String tag) {
        File baseDir = new File(directory);
        File[] files = baseDir.listFiles();
        if (files == null) {
            throw new RuntimeException("Cannot get files in" + directory);
        }
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
}
