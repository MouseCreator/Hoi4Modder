package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.RoleController;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

/**
 * Service, used to add and remove role from character
 * @param <R> - role to add/remove
 */
public class RoleSwitcher<R extends Role> {
    private RolePaneControllerPair<R> rolePaneControllerPair;
    private int targetIndex;

    private String fileDestination;
    private final CharacterItemController itemController;

    /**
     * Creates role switcher for specific role
     * @param itemController - controller of the character
     */
    public RoleSwitcher(CharacterItemController itemController) {
        this.itemController = itemController;
    }

    /**
     * Sets a pair of controller and pane, controlled by it as switcher parameter
     * @param rolePaneControllerPair - pair to set
     */
    void setPaneController(RolePaneControllerPair<R> rolePaneControllerPair) {
        this.rolePaneControllerPair = rolePaneControllerPair;
    }

    /**
     *
     * @param destination - destination of file to load ui element of the role
     */
    void setFileDestination(String destination) {
        this.fileDestination = destination;
    }

    /**
     * Index of role pane in case all roles are added. Used to determine order of panes
     * @param index - target index of role pane
     */
    void setTargetIndex(int index) {
        this.targetIndex = index;
    }

    /**
     * Connects check box and role pane, so that role is only visible, if the checkbox is checked
     * @param rolesBox - roles container
     * @param checkBox - checkbox
     * @param character - game character to add/remove role from
     */
    public void bindCheckBox(Pane rolesBox, CheckBox checkBox, GameCharacter character) {
        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(checkBox.isSelected())
                createRolePane(rolesBox, character);
            else
                destructRolePane(rolesBox, character);
        });
    }

    /**
     * Remove role pane from the roles box
     * @param rolesBox - container of the roles panes
     * @param character - character to remove role from
     */
    private void destructRolePane(Pane rolesBox, GameCharacter character) {
        if (rolePaneControllerPair.isFilled()) {
            rolesBox.getChildren().remove(rolePaneControllerPair.getPane());
            character.getRoles().remove(rolePaneControllerPair.getController().getRoleType());
            rolePaneControllerPair.clear();
            CharacterInfo characterInfo = itemController.getCharacterInfo();
            characterInfo.removePosition(targetIndex);
        }
    }

    private void createRolePane(Pane rolesBox, GameCharacter character) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CharacterItemController.class.getResource(fileDestination));
        try {
            Pane pane = loader.load();
            RoleController<R> controller = loader.getController();
            rolePaneControllerPair.update(pane, controller);
            controller.setParent(itemController);
            CharacterInfo characterInfo = itemController.getCharacterInfo();
            rolesBox.getChildren().add(characterInfo.getAndInsertPosition(targetIndex), pane);
            controller.fromCharacter(character);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return controller of the role pane
     */
    public RoleController<R> getController() {
        return rolePaneControllerPair.getController();
    }
}
