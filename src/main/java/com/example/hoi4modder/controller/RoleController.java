package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.Role;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.HashSet;
import java.util.Set;

public abstract class RoleController<R extends Role> {
    protected CharacterItem characterItem;
    public CharacterItem getCharacterItem() {
        return characterItem;
    }

    public void setCharacterItem(CharacterItem characterItem) {
        this.characterItem = characterItem;
    }
    protected void addTrait(ListView<String> listView, String trait) {
        if (trait.isEmpty())
            return;
        listView.getItems().add(trait);
    }

    protected Set<String> getTraits(ListView<String> listView) {
        ObservableList<String> items = listView.getItems();
        return new HashSet<>(items);
    }

    public abstract String filename();

    public abstract void fromRole(R role);

    public abstract R toRole();

    protected String getSelectedFromBox(ComboBox<String> box) {
        String result = box.getValue();
        result = result.replace(" ", "_");
        return result.toLowerCase();
    }

    protected void setSelectedFromBox(String slot, ComboBox<String> box) {
        slot = slot.replace("_", " ");
        slot = slot.substring(0, 1).toUpperCase() + slot.substring(1);
        box.getSelectionModel().select(slot);
    }

}
