package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.FileSearcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class PutReplaceStrategy implements SearcherStrategy {
    private FileSearcher searcher;

    private FileSearchService searchService;
    public String toModFile(String filename) {
        return filename.replace(searchService.getGameDirectory(), searchService.getModDirectory());
    }
    public PutReplaceStrategy() {
    }
    public void setSearcher(FileSearcher searcher) {
        this.searcher = searcher;
    }
    public String findCountryByTag(String tag) {
        try {
            return searcher.findCountryByTag(tag);
        } catch (NoSuchElementException e) {
            String file = searcher.findCountryByTag(tag);
            try {
            File createdFile = replaceFile(file);
                return createdFile.getAbsolutePath();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
        throw new NoSuchElementException();
    }
    private File replaceFile(String file) throws IOException {
        Path gameFile = Paths.get(file);
        String newFile = toModFile(file);
        File createdFile = new File(newFile);
        Path modPath = createdFile.toPath();
        if(createdFile.mkdirs()) {
            Files.copy(gameFile, modPath);
        } else {
            throw new IOException("Cannot create directories in path");
        }
        return createdFile;
    }
}
