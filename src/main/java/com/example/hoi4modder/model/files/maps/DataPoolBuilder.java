package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.service.ObjectPool;

/**
 * Builder for data pool
 * @param <T> class that is data pool based on
 */
public interface DataPoolBuilder<T> {
    /**
     * Loads data to Pool
     * @param objectPool - object pool to insert data
     */
    void loadData(ObjectPool objectPool);

    /**
     *
     * @return result of the building operation
     */
    DataPool<T> getResult();
}
