package com.example.hoi4modder.service;

import java.io.File;
import java.io.IOException;

public class ImageTransformer {

    public void toPortrait() throws IOException, InterruptedException {
        File charFile = new File("src/main/resources//com/example/hoi4modder/data/image/Char.dds");
        File frameFile = new File("src/main/resources/com/example/hoi4modder/data/image/frame.png");
        File script = new File("src/main/resources/com/example/hoi4modder/data/python/to_portrait.py");
        String saveDestination = "D:\\small.dds";
        ProcessBuilder processBuilder = new ProcessBuilder("python ", script.getPath() + " ", charFile.getAbsolutePath() + " ",
                frameFile.getAbsolutePath() + " ", saveDestination);

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        int exitCode = process.waitFor();
        System.out.println(exitCode);
        System.out.println(process.waitFor());
    }
}
