package com.example.hoi4modder.service;

import java.io.File;
import java.io.IOException;

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
            callScript("to_portrait", origin, frame, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toBigImage(File origin, String destination) {
        try {
            callScript("toDDS", origin.getAbsolutePath(), destination, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toSmallImage(File origin, String destination) {
        try {
            callScript("toSmallDDS", origin.getAbsolutePath(), destination, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void callScript(String name, String origin, String destination, String destination1) throws IOException, InterruptedException {
        File script = new File(Destinations.get().pythonScript(name));
        ProcessBuilder processBuilder = new ProcessBuilder("python ",
                script.getPath() + " ",
                origin + " ",
                destination + " ",
                destination1);

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        int exitCode = process.waitFor();
        System.out.println(exitCode);
        System.out.println(process.waitFor());
    }
}
