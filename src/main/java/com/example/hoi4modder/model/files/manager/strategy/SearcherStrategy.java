package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearcher;

import java.util.NoSuchElementException;

public interface SearcherStrategy {
    void setSearcher(FileSearcher searcher);

    String findCountryByTag(String tag) throws NoSuchElementException;
}
