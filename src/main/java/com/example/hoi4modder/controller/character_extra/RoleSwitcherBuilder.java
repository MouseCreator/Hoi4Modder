package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.game.roles.Advisor;

public class RoleSwitcherBuilder {
    public RoleSwitcher<Advisor> buildAdvisor(CharacterItemController itemController) {
        RoleSwitcher<Advisor> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setTargetIndex(3);
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.bindCheckBox(itemController.getAdvisorCheckBox());
        return roleSwitcher;
    }
}
