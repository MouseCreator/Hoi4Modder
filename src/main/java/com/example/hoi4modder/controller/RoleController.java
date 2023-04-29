package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Role;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.HashSet;
import java.util.Set;

/**
 * Controller for roles panes
 * @param <R> - role of the controller
 */
public abstract class RoleController<R extends Role> {
    protected CharacterItemController characterItemController;
    public CharacterItemController getCharacterItem() {
        return characterItemController;
    }

    public void setCharacterItem(CharacterItemController characterItemController) {
        this.characterItemController = characterItemController;
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
        return stringToLowerCase(result);
    }

    protected String stringToLowerCase(String origin) {
        origin = origin.replace(" ", "_");
        return origin.toLowerCase();
    }
    protected String stringToUpperCase(String origin) {
        origin = origin.replace("_", " ");
        origin = origin.substring(0, 1).toUpperCase() + origin.substring(1);
        return origin;
    }

    protected void setSelectedFromBox(String slot, ComboBox<String> box) {
        box.getSelectionModel().select(stringToUpperCase(slot));
    }

    public void setParent(CharacterItemController controller) {
        this.characterItemController = controller;
    }

    public abstract String getRoleType();

    public abstract void fromCharacter(GameCharacter character);
}
