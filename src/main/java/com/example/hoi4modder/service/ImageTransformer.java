package com.example.hoi4modder.service;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Class, used to transform images to game requirements
 * Requires python with pillow installed to use
 */
public class ImageTransformer {
    /**
     *
     * @param origin - path to large portrait of the character
     * @param frame - path to frame of the portrait
     * @param destination -path to save created portrait
     */
    public void toPortrait(String origin, String frame, String destination) {
        try {
            File script = new File(Destinations.get().pythonScript("to_portrait"));
            ProcessBuilder processBuilder = new ProcessBuilder("python ",
                    script.getPath() + " ",
                    origin + " ",
                    frame + " ",
                    destination);

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println(exitCode);
            System.out.println(process.waitFor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toBigImage(File origin, String destination) {
        try {
            File script = new File(Destinations.get().pythonScript("toDDS"));
            ProcessBuilder processBuilder = new ProcessBuilder("python ",
                    script.getPath() + " ",
                    origin.getAbsolutePath() + " ",
                    destination + " ",
                    destination);

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println(exitCode);
            System.out.println(process.waitFor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
