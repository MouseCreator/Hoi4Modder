package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;

public class RoleSwitcherBuilder {
    public RoleSwitcher<Advisor> buildAdvisorSwitcher(CharacterItemController itemController) {
        RoleSwitcher<Advisor> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(3);
        roleSwitcher.bindCheckBox(itemController.getAdvisorCheckBox());
        return roleSwitcher;
    }
    public RoleSwitcher<CountryLeader> buildCountryLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<CountryLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(0);
        roleSwitcher.bindCheckBox(itemController.getCountryLeaderBox());
        return roleSwitcher;
    }
    public RoleSwitcher<UnitLeader> buildUnitLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<UnitLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(1);
        roleSwitcher.bindCheckBox(itemController.getUnitLeaderBox());
        return roleSwitcher;
    }
    public RoleSwitcher<NavyLeader> buildNavyLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<NavyLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(2);
        roleSwitcher.bindCheckBox(itemController.getNavyLeaderBox());
        return roleSwitcher;
    }
}
