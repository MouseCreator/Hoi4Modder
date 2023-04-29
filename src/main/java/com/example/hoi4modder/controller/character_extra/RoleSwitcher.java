package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.RoleController;
import com.example.hoi4modder.game.roles.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RoleSwitcher<R extends Role> {
    private final RolePaneControllerPair<R> rolePaneControllerPair;
    private final VBox rolesBox;
    private final int targetIndex;
    private final CharacterItemController itemController;
    public RoleSwitcher(CharacterItemController itemController, RolePaneControllerPair<R> rolePaneControllerPair,
                        CheckBox checkBox, VBox roleBox, int targetIndex) {
        this.rolePaneControllerPair = rolePaneControllerPair;
        this.rolesBox = roleBox;
        this.targetIndex = targetIndex;
        this.itemController = itemController;
        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(checkBox.isSelected())
                createRolePane();
            else
                destructRolePane();
        });
    }

    private void destructRolePane() {
        if (rolePaneControllerPair.isFilled()) {
            rolesBox.getChildren().remove(rolePaneControllerPair.getPane());
            rolePaneControllerPair.clear();
        }
    }

    private void createRolePane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("country-leader-item.fxml"));
        try {
            Pane pane = loader.load();
            RoleController<R> controller = loader.getController();
            rolePaneControllerPair.update(pane, controller);
            controller.setParent(itemController);
            rolesBox.getChildren().add(targetIndex, pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
