package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;

public class FileSearchService {

    public FileSearchService(String gameDirectory, String modDirectory) {
        this.gameDirectory = gameDirectory;
        this.modDirectory = modDirectory;
    }
    public FileSearchService() {}
    public String getGameDirectory() {
        return gameDirectory;
    }

    public void setGameDirectory(String gameDirectory) {
        this.gameDirectory = gameDirectory;
    }

    public String getModDirectory() {
        return modDirectory;
    }

    public void setModDirectory(String modDirectory) {
        this.modDirectory = modDirectory;
    }

    public SearcherStrategy getStrategy() {
        return strategy;
    }

    private String gameDirectory;
    private String modDirectory;
    private String directory;
    private SearcherStrategy strategy;
    public String findCountryByTag(String tag) {
        return strategy.findCountryByTag(tag);
    }
    public void setStrategy(SearcherStrategy strategy) {
       this.strategy = strategy;
    }

    public void setDirectory(String s) {
        this.directory = directory;
    }
}
