package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.FileSearcher;

import java.util.NoSuchElementException;

/**
 * Strategy that look for file in mod directory
 * If file is absent - creates new empty file
 */
public class PutCreateStrategy implements SearcherStrategy {
    private final FileSearcher searcher = new FileSearcher();
    private FileSearchService service;

    @Override
    public String findCountryByTag(String tag) throws NoSuchElementException {
        return null;
    }

    @Override
    public void setService(FileSearchService fileSearchService) {
        this.service = fileSearchService;
    }

    @Override
    public String getInstance(String substring) {
        return null;
    }
}
