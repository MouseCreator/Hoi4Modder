package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.service.AppConfig;
import com.example.hoi4modder.service.Destinations;
import javafx.concurrent.Task;

import java.io.File;

public class RunGameTask extends Task<Void> {
    @Override
    public Void call() throws Exception {
        AppConfig appConfig = new AppConfig();
        String gameDir = appConfig.getGameDirectory();
        String gameExe = Destinations.get().gamePath(gameDir);
        Runtime.getRuntime().exec(gameExe, null, new File(gameDir));
        return null;
    }
}
