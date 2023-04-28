package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.CountryLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

/**
 * Controller for country leader
 */
public class CountryLeaderRoleController extends RoleController<CountryLeader> implements Initializable {

    @FXML
    private ComboBox<String> ideologyBox;

    @FXML
    private ListView<String> traitList;
    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField traitName;

    public CountryLeaderRoleController() {
        this.ideologies = new HashMap<>();
        ideologies.put("Democratic", List.of("conservatism liberalism socialism".split(" ")));
        ideologies.put("Communism", List.of("marxism leninism stalinism anti_revisionism anarchist_communism".split(" ")));
        ideologies.put("Fascism", List.of("nazism gen_nazism fascism_ideology falangism rexism".split(" ")));
        ideologies.put("Neutrality", List.of("despotism oligarchism anarchism moderatism centrism".split(" ")));
    }

    public void addTrait() {
        super.addTrait(traitList, traitName.getText());
    }

    @Override
    public String filename() {
        return "country-leader-item.fxml";
    }

    @Override
    public void fromRole(CountryLeader leader) {
        String type = leader.getIdeology();
        for (String ideology : ideologies.keySet()) {
            if (ideologies.get(ideology).contains(type)) {
                ideologyBox.getSelectionModel().select(ideology);
                setTypesFromIdeology(ideology);
                typeBox.getSelectionModel().select(stringToUpperCase(type));
            }
        }
        traitList.getItems().clear();
        traitList.getItems().addAll(leader.getTraits());
    }

    @Override
    public CountryLeader toRole() {
        CountryLeader leader = new CountryLeader();
        leader.setIdeology(getSelectedFromBox(typeBox));
        leader.setTraits(getTraits(traitList));
        return leader;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ideologyBox.getItems().clear();
        ideologyBox.getItems().addAll("Neutrality", "Democratic", "Fascism", "Communism");
        ideologyBox.getSelectionModel().select("Neutrality");
        setTypesFromIdeology("Neutrality");
    }
    private void setTypesFromIdeology(String ideology) {
        typeBox.getItems().clear();
        List<String> list = ideologies.get(ideology);
        for(String str : list)
            typeBox.getItems().add(stringToUpperCase(str));
    }
    private final Map<String, List<String>> ideologies;


    @FXML
    void updateIdeologyType() {
        setTypesFromIdeology(ideologyBox.getValue());
        typeBox.getSelectionModel().select(0);
    }
}
