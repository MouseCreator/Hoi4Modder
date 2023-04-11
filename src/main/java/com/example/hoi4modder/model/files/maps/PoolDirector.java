package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.model.files.manager.FileSearchService;

/**
 * Director for data pool builders
 */
public class PoolDirector {
    private DataPoolBuilder<String> builder;

    /**
     *
     * @param builder - builder to use for creation
     */
    public void setBuilder(DataPoolBuilder<String> builder) {
        this.builder = builder;
    }

    /**
     *
     * @param service - file searcher to load data
     * @return data pool created by builder
     */
    public DataPool<String> makeDataPool(FileSearchService service) {
        builder.loadData(service);
        return builder.getResult();
    }
}
