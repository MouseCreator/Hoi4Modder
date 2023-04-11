package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.model.files.manager.FileSearchService;

/**
 * BUILDER.
 * Builder for data pool
 * @param <T> class that is data pool based on
 */
public interface DataPoolBuilder<T> {
    /**
     * Loads data to Pool
     * @param fileSearchService - file searcher to load files
     */
    void loadData(FileSearchService fileSearchService);

    /**
     *
     * @return result of the building operation
     */
    DataPool<T> getResult();
}
