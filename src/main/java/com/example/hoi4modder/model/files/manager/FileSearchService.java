package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;
import com.example.hoi4modder.service.AppConfig;
import com.example.hoi4modder.service.Destinations;

/**
 * Service to find files with specified strategy
 */
public class FileSearchService {
    private String gameDirectory;
    private String modDirectory;
    private String directory;
    public FileSearchService(String gameDirectory, String modDirectory) {
        this.gameDirectory = gameDirectory;
        this.modDirectory = modDirectory;
    }

    /**
     * Example of file searcher
     */
    public FileSearchService() {
        AppConfig appConfig = new AppConfig();
        this.gameDirectory = appConfig.getGameDirectory();
        this.modDirectory = appConfig.getModDirectory();
    }
    public String getGameDirectory() {
        return gameDirectory;
    }
    public void setGameDirectory(String gameDirectory) {
        this.gameDirectory = gameDirectory + Destinations.get().separator();
    }

    public String getModDirectory() {
        return modDirectory;
    }

    public void setModDirectory(String modDirectory) {
        this.modDirectory = modDirectory + Destinations.get().separator();
    }
    private SearcherStrategy strategy;

    /**
     *
     * @param tag - country to find
     * @return name of file that contains country tag
     */
    public String findCountryByTag(String tag) {
        return strategy.findCountryByTag(tag);
    }

    /**
     *
     * @param strategy - strategy to use, when looking for files
     */
    public void setStrategy(SearcherStrategy strategy) {
       this.strategy = strategy;
       strategy.setService(this);
    }

    public void setDirectory(String s) {
        this.directory = s;
    }

    public String getFullModDirectory() {
        return modDirectory + directory;
    }

    public String getFullGameDirectory() {
        return gameDirectory + Destinations.get().separator() + directory;
    }

    /**
     *
     * @param substring - target substring
     * @return first instance of file that contains specified substring
     */
    public String findInstance(String substring) {
        return strategy.getInstance(substring);
    }
}
