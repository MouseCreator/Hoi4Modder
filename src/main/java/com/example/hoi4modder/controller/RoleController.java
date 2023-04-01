package com.example.hoi4modder.controller;

import javafx.scene.control.ListView;

public abstract class RoleController {
    protected CharacterItem characterItem;
    public CharacterItem getCharacterItem() {
        return characterItem;
    }

    public void setCharacterItem(CharacterItem characterItem) {
        this.characterItem = characterItem;
    }
    public void addTrait(ListView<String> listView, String trait) {
        listView.getItems().add(trait);
    }

}
