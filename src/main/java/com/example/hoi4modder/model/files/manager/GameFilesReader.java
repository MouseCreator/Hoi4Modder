package com.example.hoi4modder.model.files.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameFilesReader {

    public List<String> readByLines(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            lines.add(line);
        }
        return lines;
    }
    public String read(String filename) throws IOException {
        return String.join(" ", readByLines(filename));
    }
}
