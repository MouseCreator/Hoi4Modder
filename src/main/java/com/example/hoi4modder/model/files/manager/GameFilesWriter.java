package com.example.hoi4modder.model.files.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that writes info to file
 */
public class GameFilesWriter {
    /**
     *
     * @param filename - file to write in
     * @param message - message to write
     * @throws IOException if an error occurred during reading a file
     */
    public void write(String filename, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(message);
        writer.close();
    }
}
