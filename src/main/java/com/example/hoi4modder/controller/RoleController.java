package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Role;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.HashSet;
import java.util.Set;

/**
 * Controller for roles panes
 * @param <R> - role of the controller
 */
public abstract class RoleController<R extends Role> {
    protected CharacterItemController characterItemController;


    protected void initializeContextMenu(ListView<String> listView) {
        listView.setCellFactory(listV -> {

            ListCell<String> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = addEditItem(cell);
            MenuItem deleteItem = addDeleteItem(listView, cell);
            contextMenu.getItems().addAll(editItem, deleteItem);
            cell.textProperty().bind(cell.itemProperty());
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });
    }

    private static MenuItem addDeleteItem(ListView<String> listView, ListCell<String> cell) {
        MenuItem deleteItem = new MenuItem();
        deleteItem.textProperty().bind(Bindings.format("Delete"));
        deleteItem.setOnAction(event -> listView.getItems().remove(cell.getItem()));
        return deleteItem;
    }

    private static MenuItem addEditItem(ListCell<String> cell) {
        MenuItem editItem = new MenuItem();
        editItem.textProperty().bind(Bindings.format("Copy"));
        editItem.setOnAction(event -> addToClipboard(cell));
        return editItem;
    }

    private static void addToClipboard(ListCell<String> cell) {
        String item = cell.getItem();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(item);
        clipboard.setContent(content);
    }

    public CharacterItemController getCharacterItem() {
        return characterItemController;
    }

    public void setCharacterItem(CharacterItemController characterItemController) {
        this.characterItemController = characterItemController;
    }
    protected void addTrait(ListView<String> listView, String trait) {
        trait =  trait.replace('\t', ' ').trim();
        if (cannotBeAdded(listView, trait))
            return;
        listView.getItems().add(trait);
    }

    private boolean cannotBeAdded(ListView<String> listView, String trait) {
        return trait.isEmpty() || listView.getItems().contains(trait);
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

    protected void removeTrait(ListView<String> traitList) {
        ObservableList<String> selectedItems = traitList.getSelectionModel().getSelectedItems();
        String[] strings = new String[selectedItems.size()];
        selectedItems.toArray(strings);
        for (String s : strings) {
            traitList.getItems().remove(s);
        }
    }
}
