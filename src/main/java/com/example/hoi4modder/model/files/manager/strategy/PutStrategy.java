package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.FileSearcher;

import java.util.NoSuchElementException;

/**
 * Strategy to look for file only in mod directory
 */
public class PutStrategy implements SearcherStrategy {
    private final FileSearcher searcher = new FileSearcher() ;
    private FileSearchService searchService;

    @Override
    public String findCountryByTag(String tag) throws NoSuchElementException {
        searcher.setDirectory(searchService.getFullModDirectory());
        return searcher.findCountryByTag(tag);
    }

    @Override
    public void setService(FileSearchService fileSearchService) {
        this.searchService = fileSearchService;
    }

    @Override
    public String getInstance(String substring) {
        searcher.setDirectory(searchService.getFullModDirectory());
        return searcher.findInstance(substring);
    }
}
