package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.model.files.manager.strategy.PutCreateStrategy;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.model.files.manager.strategy.PutStrategy;
import com.example.hoi4modder.model.files.manager.strategy.SearcherStrategy;
import com.example.hoi4modder.service.Destinations;

public class FileSearch {
    private final FileSearchService service;

    private FileSearch(SearcherStrategy strategy) {
        this.service = new FileSearchService();
        service.setStrategy(strategy);
    }
    public static FileSearch createPutCreateService() {
        return new FileSearch(new PutCreateStrategy());
    }
    public static FileSearch createPutService() {
        return new FileSearch(new PutStrategy());
    }
    public static FileSearch createPutReplaceService() {
        return new FileSearch(new PutReplaceStrategy());
    }

    public String findCountryByTag(String tag) {
        service.setDirectory(Destinations.get().characters());
        return service.findCountryByTag(tag);
    }
}
