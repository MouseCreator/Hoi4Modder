package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearchService;

import java.util.NoSuchElementException;

/**
 * Strategy, used to search files
 */
public interface SearcherStrategy {

    String findCountryByTag(String tag) throws NoSuchElementException;

    void setService(FileSearchService fileSearchService);

    String getInstance(String substring);
}
