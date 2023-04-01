package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;
import com.example.hoi4modder.service.Destinations;

public class FileSearchService {

    public FileSearchService(String gameDirectory, String modDirectory) {
        this.gameDirectory = gameDirectory;
        this.modDirectory = modDirectory;
    }
    public FileSearchService() {
        this.gameDirectory = "D:\\Games\\Hoi4 1.10\\Hearts of Iron IV";
        this.modDirectory = "C:\\Users\\mysha\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\leylamod12d";
    }
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
       strategy.setService(this);
    }

    public void setDirectory(String s) {
        this.directory = s;
    }

    public String getFullModDirectory() {
        return modDirectory + Destinations.get().separator() + directory;
    }

    public String getFullGameDirectory() {
        return gameDirectory + Destinations.get().separator() + directory;
    }

    public String findInstance(String substring) {
        return strategy.getInstance(substring);
    }
}
