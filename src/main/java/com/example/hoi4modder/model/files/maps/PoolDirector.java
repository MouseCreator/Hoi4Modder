package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.service.ObjectPool;

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
     * @param pool - object pool to load service
     * @return data pool created by builder
     */
    public DataPool<String> makeDataPool(ObjectPool pool) {
        builder.loadData(pool);
        return builder.getResult();
    }
}
