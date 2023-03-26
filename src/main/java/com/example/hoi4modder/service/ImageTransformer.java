package com.example.hoi4modder.service;

import java.io.File;
import java.io.IOException;
public class ImageTransformer {

    public void toPortrait() throws IOException, InterruptedException {
        File charFile = new File("src/main/resources//com/example/hoi4modder/data/image/Char.dds");
        File frameFile = new File("src/main/resources/com/example/hoi4modder/data/image/frame.png");
        File script = new File("src/main/resources/com/example/hoi4modder/data/python/to_portrait.py");
        String saveDestination = "D:\\";
        ProcessBuilder builder = new ProcessBuilder("python",
                script.getAbsolutePath(), charFile.getAbsolutePath(), frameFile.getAbsolutePath(), saveDestination);
        Process process = builder.start();

        System.out.println(process.waitFor());
    }
    public void portrait() {
        File charFile = new File("src/main/resources/com/example/hoi4modder/data/image/Char.dds");
        File frameFile = new File("src/main/resources/com/example/hoi4modder/data/image/frame.png");
    }
}
