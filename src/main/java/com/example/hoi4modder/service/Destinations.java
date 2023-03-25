package com.example.hoi4modder.service;

import java.io.File;

public class Destinations {
    private Destinations destinations;
    public Destinations getDestinations() {
        return destinations;
    }
    private final String s = File.pathSeparator;
    private final String history = "history" + s;
    private final String gfx = "gfx" + s;
    private final String common = "common" + s;
    private final String txt = ".txt";
    private final String countries = "countries" + s;

    private final String states = "states" + s;
    public String countryFile(String tag) {
        return history + countries + tag + txt;
    }
    public String stateFile(int id) {
        return history + states + id + txt;
    }

}
