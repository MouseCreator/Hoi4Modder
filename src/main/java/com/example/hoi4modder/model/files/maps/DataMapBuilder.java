package com.example.hoi4modder.model.files.maps;

public interface DataMapBuilder<T> {
    void newHashMap();
    DataMap<T> getResult();

    void setFilename(String filename);

    void setChangeable(boolean changeable);
}
