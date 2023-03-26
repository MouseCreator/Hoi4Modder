package com.example.hoi4modder.model.files.reader;

import com.example.hoi4modder.model.files.properties.SavedElement;
import com.example.hoi4modder.model.files.properties.SavedList;
import com.example.hoi4modder.model.files.properties.SavedListArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileReaderImpl {
    private final String[] delimiterList = {"{", "}"};

    public SavedElement read(String filename) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        Queue<String> expressions = new LinkedBlockingQueue<>();
        while (true) {
            String s = fileReader.readLine();
            if (s == null)
                break;
            s = toNormalizedExpression(s);
            if (s.isEmpty())
                continue;
        }
        return null;
    }
    public String toNormalizedExpression(String input) {
        String output = input.replaceAll("\t", "");
        if (output.contains("#")) {
            output = output.split("#", 2)[0];
        }
        return output.trim();
    }

    public SavedList fromLines(List<String> lines) {
        SavedListArray array = new SavedListArray();
        return null;
    }
}
