package com.example.hoi4modder.service;

import java.io.File;
import java.io.IOException;

public class ImageTransformer {

    public void toPortrait(String origin, String frame, String destination) throws IOException, InterruptedException {
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
}
