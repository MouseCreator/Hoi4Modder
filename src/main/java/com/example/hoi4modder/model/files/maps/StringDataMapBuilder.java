package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;

public class StringDataMapBuilder implements DataMapBuilder<String> {
    private DataMap<String> map;

    public StringDataMapBuilder() {
        newHashMap();
    }

    public DataMap<String> getResult() {
        return map;
    }

    public void newHashMap() {
        map = new DataMap<>(new HashMap<>());
    }

    public void setFilename(String filename) {
        map.setFilename(filename);
    }
    public void setChangeable(boolean isChangeable) {
        map.setReadOnly(isChangeable);
    }
}
