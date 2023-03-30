package com.example.hoi4modder.game.collection;

public abstract class SavedCollection<T> {
    protected String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
