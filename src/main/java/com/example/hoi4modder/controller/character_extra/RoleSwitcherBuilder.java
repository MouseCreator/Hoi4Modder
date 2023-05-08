package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;

/**
 * BUILDER PATTERN.
 * Used to create instances of role switchers
 */
public class RoleSwitcherBuilder {
    /**
     * Creates country leader role switcher
     * @param itemController - controller of character item
     * @return role switcher for country leader role
     */
    public RoleSwitcher<CountryLeader> buildCountryLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<CountryLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(0);
        roleSwitcher.setFileDestination("country-leader-item.fxml");
        return roleSwitcher;
    }
    /**
     * Creates advisor role switcher
     * @param itemController - controller of character item
     * @return role switcher for advisor role
     */
    public RoleSwitcher<Advisor> buildAdvisorSwitcher(CharacterItemController itemController) {
        RoleSwitcher<Advisor> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(1);
        roleSwitcher.setFileDestination("advisor-item.fxml");
        return roleSwitcher;
    }
    /**
     * Creates unit leader role switcher
     * @param itemController - controller of game character item
     * @return role switcher for leader item
     */
    public RoleSwitcher<UnitLeader> buildUnitLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<UnitLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(2);
        roleSwitcher.setFileDestination("unit-leader-item.fxml");
        return roleSwitcher;
    }
    /**
     * Creates navy leader role switcher
     * @param itemController - controller of character item
     * @return role switcher for navy leader role
     */
    public RoleSwitcher<NavyLeader> buildNavyLeaderSwitcher(CharacterItemController itemController) {
        RoleSwitcher<NavyLeader> roleSwitcher = new RoleSwitcher<>(itemController);
        roleSwitcher.setPaneController(new RolePaneControllerPair<>());
        roleSwitcher.setTargetIndex(3);
        roleSwitcher.setFileDestination("navy-leader-item.fxml");
        return roleSwitcher;
    }
}
