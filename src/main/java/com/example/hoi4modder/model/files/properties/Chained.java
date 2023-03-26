package com.example.hoi4modder.model.files.properties;

public interface Chained {
    boolean isDelimiter(String str);
    Property fromFile();
    Property delegate();
}
