package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.service.ObjectPool;

public interface DataPoolBuilder<T> {
    void loadData(ObjectPool objectPool);

    DataPool<T> getResult();
}
