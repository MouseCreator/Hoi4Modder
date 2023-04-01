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
    private final FileSearcher searcher = new FileSearcher();
    private FileSearchService searchService;
    public String toModFile(String filename) {
        return filename.replace(searchService.getGameDirectory(), searchService.getModDirectory());
    }
    public PutReplaceStrategy() {
    }
    public String findCountryByTag(String tag) {
        searcher.setDirectory(searchService.getFullModDirectory());
        try {
            return searcher.findCountryByTag(tag);
        } catch (NoSuchElementException e) {
            String file = searcher.findCountryByTag(tag);
            String createdFile = getCreatedFile(file);
            if (createdFile != null) return createdFile;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void setService(FileSearchService fileSearchService) {
        this.searchService = fileSearchService;
    }

    @Override
    public String getInstance(String substring) {
        searcher.setDirectory(searchService.getFullModDirectory());
        try {
            return searcher.findInstance(substring);
        } catch (NoSuchElementException e) {
            String file = searcher.findInstance(substring);
            String createdFile = getCreatedFile(file);
            if (createdFile != null) return createdFile;
        }
        throw new NoSuchElementException();
    }

    private String getCreatedFile(String file) {
        try {
            File createdFile = replaceFile(file);
            return createdFile.getAbsolutePath();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return null;
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
