package com.example.hoi4modder.service;

import java.io.File;

public class Destinations {
    private static final Destinations destinations = new Destinations();
    public static Destinations get() {
        return destinations;
    }
    private final String s = File.separator;
    private final String history = "history" + s;
    private final String gfx = "gfx" + s;
    private final String common = "common" + s;
    private final String characters = "characters" + s;
    private final String basedir = "src/main/resources/com/example/hoi4modder/";
    private final String python = "python" + s;

    private final String data = "data" + s;

    private final String image = "image" + s;
    private final String txt = ".txt";

    private final String py = ".py";
    private final String countries = "countries" + s;

    private final String localisation = "localisation" + s;

    private final String russian = "russian" + s;
    private final String yml = ".yml";

    private final String mod_countries = "mod_countries_l_russian";
    private final String mod_characters = "mod_characters_l_russian";

    private final String interfaceDir = "interface" + s;
    private final String states = "states" + s;
    public String stateFile(int id) {
        return history + states + id + txt;
    }

    public String pythonScript(String filename) {
        return basedir + data + python + filename + py;
    }

    public String characters() {
        return common + characters;
    }

    public String localisation(String filename) {
        return localisation + russian + s + yml;
    }
    public String localisation() {
        return localisation + russian;
    }

    public String separator() {
        return s;
    }

    public String interfaceDir() {
        return interfaceDir;
    }

    public String testPortrait() {
        return basedir + data + image + "Char.dds";
    }
}
