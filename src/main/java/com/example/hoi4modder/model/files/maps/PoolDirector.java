package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.service.ObjectPool;

public class PoolDirector {
    private DataPoolBuilder<String> builder;

    public void setBuilder(DataPoolBuilder<String> builder) {
        this.builder = builder;
    }

    public DataPool<String> makeDataPool(ObjectPool pool) {
        builder.loadData(pool);
        return builder.getResult();
    }
}
