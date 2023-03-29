package com.example.hoi4modder.model.files.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameFilesWriter {
    public void write(String filename, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(message);
        writer.close();
    }
}
