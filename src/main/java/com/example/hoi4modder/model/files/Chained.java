package com.example.hoi4modder.model.files;

public interface Chained {
    boolean isDelimiter(String str);
    Property fromFile();
    Property delegate();
}
