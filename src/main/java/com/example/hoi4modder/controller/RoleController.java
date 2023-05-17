package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.command.*;
import com.example.hoi4modder.controller.command.roles.UndoRedoManager;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Role;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller for roles panes
 * @param <R> - role of the controller
 */
public abstract class RoleController<R extends Role> implements ControlConnectable {
    protected CharacterItemController characterItemController;
    protected ControlConnector controlConnector;
    @Override
    public ControlConnector getConnector() {
        return controlConnector;
    }

    @Override
    public ControlConnectableCallable callSelf() {
        return new ControlConnectableCallable() {
            @Override
            public ControlConnectable call() {
                CharacterItemController parent = (CharacterItemController) characterItemController.callSelf().call();
                return parent.getRoleController(roleString());
            }
        };
    }

    protected abstract String roleString();

    protected UndoRedoManager undoRedoManager;
    protected void initializeControlConnector(Initializable controller) {
        controlConnector = ControlIndexConnector.getArrayConnector();
        controlConnector.initialize(controller);

    }

    /**
     * Adds context menu on right click on traits list item
     * @param listView - traits list
     */
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
        listView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removeTrait(listView);
            }
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

    /**
     * Adds trait to trait list
     * @param listView - trait list
     * @param field - text field to get trait name
     */
    protected void addTrait(ListView<String> listView, TextField field) {
        String trait = field.getText();
        if (trait == null)
            return;
        trait =  trait.replace('\t', ' ').trim();
        if (cannotBeAdded(listView, trait))
            return;
        listView.getItems().add(trait);
        field.setText("");
    }

    protected void initializeTextField(ListView<String> listView, TextField traitField) {
        traitField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                addTrait(listView, traitField);
            }
        });
    }

    private boolean cannotBeAdded(ListView<String> listView, String trait) {
        return trait.isEmpty() || listView.getItems().contains(trait);
    }

    /**
     *
     * @param listView - trait list
     * @return traits for the character's role
     */
    protected Set<String> getTraits(ListView<String> listView) {
        ObservableList<String> items = listView.getItems();
        return new HashSet<>(items);
    }

    /**
     *
     * @return source file for role GUI element
     */
    public abstract String filename();

    /**
     *
     * @param role - role to generate element
     */
    public abstract void fromRole(R role);

    /**
     *
     * @return convert current item to role
     */
    public abstract R toRole();

    /**
     *
     * @param origin - string in GUI case, containing scapes and capital letters
     * @return string in snake case
     */
    protected String stringToLowerCase(String origin) {
        origin = origin.replace(" ", "_");
        return origin.toLowerCase();
    }

    /**
     *
     * @param origin - string in snake case
     * @return string in GUI case
     */
    protected String stringToUpperCase(String origin) {
        origin = origin.replace("_", " ");
        origin = origin.substring(0, 1).toUpperCase() + origin.substring(1);
        return origin;
    }

    /**
     *
     * @param slot - slot to be selected in combobox
     * @param box - box to be modified
     */
    protected void setSelectedFromBox(String slot, ComboBox<String> box) {
        box.getSelectionModel().select(stringToUpperCase(slot));
    }

    /**
     *
     * @param controller - controller of the character item
     */
    public void setParent(CharacterItemController controller) {
        this.characterItemController = controller;
        onSetParent();
    }
    protected void onSetParent(){
        initConnector();
    }

    /**
     *
     * @return role name for character
     */
    public abstract String getRoleType();

    /**
     * Creates role from character
     * @param character - game character to get role from
     */
    public abstract void fromCharacter(GameCharacter character);

    /**
     * Remove selected trait from the list
     * @param traitList - trait list
     */
    protected void removeTrait(ListView<String> traitList) {
        ObservableList<String> selectedItems = traitList.getSelectionModel().getSelectedItems();
        String[] strings = new String[selectedItems.size()];
        selectedItems.toArray(strings);
        for (String s : strings) {
            traitList.getItems().remove(s);
        }
    }

    public abstract void initConnector();
}
