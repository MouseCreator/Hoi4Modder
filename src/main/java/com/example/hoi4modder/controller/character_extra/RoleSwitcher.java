package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.RoleController;
import com.example.hoi4modder.game.roles.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class RoleSwitcher<R extends Role> {
    private RolePaneControllerPair<R> rolePaneControllerPair;
    private int targetIndex;

    private String fileDestination;
    private final CharacterItemController itemController;
    public RoleSwitcher(CharacterItemController itemController) {
        this.itemController = itemController;
    }
    void setPaneController(RolePaneControllerPair<R> rolePaneControllerPair) {
        this.rolePaneControllerPair = rolePaneControllerPair;;
    }

    void setFileDestination(String destination) {
        this.fileDestination = destination;
    }
    void setTargetIndex(int index) {
        this.targetIndex = index;
    }
    public void bindCheckBox(Pane rolesBox, CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(checkBox.isSelected())
                createRolePane(rolesBox);
            else
                destructRolePane(rolesBox);
        });
    }
    private void destructRolePane(Pane rolesBox) {
        if (rolePaneControllerPair.isFilled()) {
            rolesBox.getChildren().remove(rolePaneControllerPair.getPane());
            rolePaneControllerPair.clear();
        }
    }

    private void createRolePane(Pane rolesBox) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CharacterItemController.class.getResource(fileDestination));
        try {
            Pane pane = loader.load();
            RoleController<R> controller = loader.getController();
            rolePaneControllerPair.update(pane, controller);
            controller.setParent(itemController);
            rolesBox.getChildren().add(Math.min(targetIndex,rolesBox.getChildren().size()), pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RoleController<R> getController() {
        return rolePaneControllerPair.getController();
    }
}
