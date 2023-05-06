package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.game.GameCharacter;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import java.util.List;

public interface CharacterCreator {
    void addCharacter(ObservableList<Pane> panes, List<CharacterItemController> controllerList, GameCharacter character);
}
