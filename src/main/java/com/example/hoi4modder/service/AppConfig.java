package com.example.hoi4modder.service;

public class AppConfig {

     private static String gameDirectory = "D:\\Games\\Hoi4 1.12\\Hearts of Iron IV\\";
     private static String modDirectory = "C:\\Users\\mysha\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\leylamod12d\\";

    public String getModDirectory() {
        return modDirectory;
    }

    public void setModDirectory(String modDirectory) {
        AppConfig.modDirectory = modDirectory;
    }

    public String getGameDirectory() {
        return gameDirectory;

    }

    public void setGameDirectory(String gameDirectory) {
        AppConfig.gameDirectory = gameDirectory;
    }
}
