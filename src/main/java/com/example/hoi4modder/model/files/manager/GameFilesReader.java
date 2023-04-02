package com.example.hoi4modder.model.files.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads file from the game
 */
public class GameFilesReader {
    /**
     *
     * @param filename - file to read
     * @return lines of the file
     * @throws IOException if an error occurred during reading a file or file wasn't found
     */
    public List<String> readByLines(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    /**
     *
     * @param filename - file to read
     * @return file's content
     * @throws IOException if an error occurred during reading a file or file wasn't found
     */
    public String read(String filename) throws IOException {
        return String.join("\n", readByLines(filename));
    }
}
