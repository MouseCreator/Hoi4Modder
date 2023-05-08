package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.RoleController;
import com.example.hoi4modder.game.roles.Role;
import javafx.scene.layout.Pane;

/**
 * Pair, containing controller and pane, controlled by it
 * @param <R> role model of the controller
 */
public class RolePaneControllerPair<R extends Role> {
    private Pane pane;
    private RoleController<R> roleController;

    /**
     *
     * @param pane - pane to set in the pair
     * @param roleController - role controller to set in the pair
     */
    public void update(Pane pane, RoleController<R> roleController) {
        this.pane = pane;
        this.roleController = roleController;
    }

    /**
     *
     * @return controller for role pane
     */
    public RoleController<R> getController() {
        return roleController;
    }

    /**
     *
     * @return pane to show role parameters
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * Removes pane and controller from the pair
     */
    public void clear() {
        pane = null;
        roleController = null;
    }

    /**
     *
     * @return true if pair contains values
     */
    public boolean isFilled() {
        return pane != null;
    }
}
