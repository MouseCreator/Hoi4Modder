package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;

import java.io.File;
import java.util.NoSuchElementException;

public class FileSearcher {

    private String directory;

    public String directoryFull;

    public String getGameDirectory() {
        return gameDirectory;
    }

    public void setGameDirectory(String gameDirectory) {
        this.gameDirectory = gameDirectory;
    }

    private String gameDirectory;
    private String modDirectory;

    private String fromGame() {
        return gameDirectory;
    }
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
        File baseDir = new File(directoryFull);
        File[] files = baseDir.listFiles();
        if (files == null) {
            throw new RuntimeException("Cannot get files in" + directoryFull);
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

    public String getModDirectory() {
        return modDirectory;
    }

    public void setModDirectory(String modDirectory) {
        this.modDirectory = modDirectory;
    }

    public void setFullDirectory(String begin) {
        this.directoryFull = begin + directory;
    }

    public String toModFile(String filename) {
        return filename.replace(gameDirectory, modDirectory);
    }
    private SearcherStrategy strategy;
    public void setStrategy(SearcherStrategy strategy) {
        strategy.setSearcher(this);
        this.strategy = strategy;
    }
}
