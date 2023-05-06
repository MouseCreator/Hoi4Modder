package com.example.hoi4modder.controller;

public interface ListItemController<T> {
    void fromModel(T model);

    T toModel();

    void setParent(CharacterListEditor editor);
}
