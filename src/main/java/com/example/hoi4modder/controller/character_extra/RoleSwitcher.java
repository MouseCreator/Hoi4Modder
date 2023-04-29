package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.RoleController;
import com.example.hoi4modder.game.roles.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class RoleSwitcher<R extends Role> {
    private RolePaneControllerPair<R> rolePaneControllerPair;
    private Pane rolesBox;
    private int targetIndex;
    private final CharacterItemController itemController;
    public RoleSwitcher(CharacterItemController itemController) {
        this.itemController = itemController;
    }
    void setPaneController(RolePaneControllerPair<R> rolePaneControllerPair) {
        this.rolePaneControllerPair = rolePaneControllerPair;;
    }
    void setRolesBox(Pane box) {
        this.rolesBox = box;
    }
    void setTargetIndex(int index) {
        this.targetIndex = index;
    }
    public void bindCheckBox(CheckBox checkBox) {
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
