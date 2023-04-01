package com.example.hoi4modder.model.files.manager.strategy;

import com.example.hoi4modder.model.files.manager.FileSearcher;

public class PutCreateStrategy implements SearcherStrategy {
    private FileSearcher searcher;

    @Override
    public void setSearcher(FileSearcher searcher) {
        this.searcher = searcher;
    }
}
