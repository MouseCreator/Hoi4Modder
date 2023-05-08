package com.example.hoi4modder.service;

import java.io.File;

/**
 * Class that saves static path values for file services
 */
public class Destinations {
    private static final Destinations destinations = new Destinations();
    private final String fileNotation = "file:";

    public static Destinations get() {
        return destinations;
    }
    private final String s = File.separator;
    private final String history = "history" + s;
    private final String gfx = "gfx" + s;

    private final String ideas = "ideas" + s;
    private final String common = "common" + s;
    private final String leaders = "leaders" + s;
    private final String characters = "characters" + s;
    private final String basedir = "src/main/resources/com/example/hoi4modder/";
    private final String python = "python" + s;

    private final String data = "data" + s;

    private final String image = "image" + s;
    private final String txt = ".txt";
    private final String py = ".py";
    private final String localisation = "localisation" + s;

    private final String russian = "russian" + s;
    private final String yml = ".yml";
    private final String interfaceDir = "interface" + s;
    private final String states = "states" + s;

    private final String iconsDir = "icons" + s;

    /**
     *
     * @param filename - name of python script (without py included)
     * @return path to python script
     */
    public String pythonScript(String filename) {
        return basedir + data + python + filename + py;
    }

    /**
     *
     * @return path to characters folder
     */
    public String characters() {
        return common + characters;
    }

    /**
     *
     * @return path to localisation folder
     */
    public String localisation() {
        return localisation + russian;
    }

    /**
     *
     * @return system file path separator
     */
    public String separator() {
        return s;
    }

    /**
     *
     * @return position of interface base directory
     */
    public String interfaceDir() {
        return interfaceDir;
    }

    /**
     *
     * @return image for portraits test
     */
    public String testPortrait() {
        return basedir + data + image + "Char.dds";
    }

    public String frameImage() {
        return basedir + data + image + "frame.png";
    }

    public String ideasGFX() {
        return gfx + interfaceDir + ideas;
    }

    public String leaderGFX(String tag) {
        return gfx + leaders + tag + s;
    }

    public String noSmallPortrait() {
        return basedir + data + image + interfaceDir + "NoIdea.png";
    }
    public String noLargePortrait() {
        return basedir + data + image + interfaceDir + "NoLeader.png";
    }

    public String gamePath(String gameDirectory) {
        return gameDirectory + "hoi4.exe";
    }

    public String icon() {
        return fileNotation + basedir + data + image + iconsDir + "IconModder.png";
    }
}
