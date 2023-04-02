package com.example.hoi4modder.model.files.manager;

import java.io.File;
import java.util.NoSuchElementException;

/**
 * Class to search for files in directories
 */
public class FileSearcher {

    private String directory;

    /**
     * Sets directory to searcher
     * @param directory - directory to look in
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Creates file searcher with specified directory
     * @param directory - directory to look in
     */
    public FileSearcher(String directory) {
        this.directory = directory;
    }
    public FileSearcher() {
    }

    /**
     *
     * @param tag - country tag (AAA)
     * @return filename for file that contains specified tag
     */
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

    /**
     *
     * @param filename - file to find
     * @return full path to the file
     */
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

    /**
     * Finds file that has substring in name
     * @param substring - target substring
     * @return first instance of the file that contains {@param substring}
     */
    public String findInstance(String substring) {
        File[] files = getFilesFromDirectory();
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                continue;
            }
            String name = fileEntry.getName();
            if (name.contains(substring))
                return fileEntry.getPath();
        }
        throw new NoSuchElementException("Cannot find file with " + substring + " at " + directory);
    }
}
