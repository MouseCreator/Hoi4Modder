package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearcher;
import com.example.hoi4modder.model.files.manager.GameFilesReader;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class PutReplaceStrategy implements SearcherStrategy {
    private FileSearcher searcher;

    public PutReplaceStrategy() {
    }
    public void setSearcher(FileSearcher searcher) {
        this.searcher = searcher;
    }
    public String findCountryByTag(String tag) throws IOException {
        searcher.setFullDirectory(searcher.getModDirectory());
        try {
            return searcher.findCountryByTag(tag);
        } catch (NoSuchElementException e) {
            searcher.setFullDirectory(searcher.getGameDirectory());
            String file = searcher.findCountryByTag(tag);
            File createdFile = replaceFile(file);
            return createdFile.getAbsolutePath();
        }
    }
    private File replaceFile(String file) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        String msg = reader.read(file);
        String newFile = searcher.toModFile(file);
        GameFilesWriter writer = new GameFilesWriter();
        File createdFile = new File(newFile);
        if(createdFile.mkdirs()) {
            writer.write(createdFile.getAbsolutePath(), msg);
        } else {
            throw new IOException("Cannot create directories in path");
        }
        return createdFile;
    }
}
