package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;

public class RoleSwitcherBuilder {
    public RoleSwitcher<CountryLeader> buildCountryLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<CountryLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(0);
        return roleSwitcher;
    }
    public RoleSwitcher<UnitLeader> buildUnitLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<UnitLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(1);
        return roleSwitcher;
    }
    public RoleSwitcher<NavyLeader> buildNavyLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<NavyLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(2);
        return roleSwitcher;
    }
    public RoleSwitcher<Advisor> buildAdvisorSwitcher(CharacterItemController itemController) {
        RoleSwitcher<Advisor> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setRolesBox(itemController.getRolesBox());
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(3);
        return roleSwitcher;
    }
}
