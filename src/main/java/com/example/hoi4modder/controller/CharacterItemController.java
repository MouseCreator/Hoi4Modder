package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.FieldValueMap;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CharacterRole;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.model.files.images.DirectSurfaceManager;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterItemController implements Initializable {
    @FXML
    private CheckBox advisorBox;

    @FXML
    private Button autoSmallPortrait;

    @FXML
    private ImageView bigPortraitImage;

    @FXML
    private TextField characterIDField;

    @FXML
    private TextField characterNameField;

    @FXML
    private CheckBox countryLeaderBox;

    @FXML
    private CheckBox navyLeaderBox;

    @FXML
    private HBox rolesBox;

    @FXML
    private ImageView smallPortraitImage;

    @FXML
    private CheckBox unitLeaderBox;

    private CharacterListEditor listEditor;

    private boolean hasBigPortrait = false;
    private boolean hasSmallPortrait = false;

    public void setParent(CharacterListEditor editor) {
        this.listEditor = editor;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void fromCharacter(GameCharacter character) {
        DataPool<String> localisationPool = ((LoadedData)listEditor.parentController.getObjectPool().get("data")).getLocalisationData();
        characterIDField.setText(character.getIdentification());
        characterNameField.setText(localisationPool.get(character.getName()));
        loadPortraits(character);
        loadRoles(character);
    }

    private void loadRoles(GameCharacter character) {
        try {
            for (String role : character.getRoles().keys()) {
                if (role.equals("advisor")) {
                    loadAdvisor(character, role);
                } else if (role.equals("country_leader")) {
                    loadCountryLeader(character, role);
                } else if (role.equals("corps_commander")) {
                    loadCountryLeader(character, role);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCountryLeader(GameCharacter character, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        CountryLeader countryLeader = (CountryLeader) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("country-leader-item.fxml"));
        Pane pane = loader.load();
        rolesBox.getChildren().add(pane);
        CountryLeaderRoleController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(countryLeader);
        countryLeaderBox.setSelected(true);
    }

    private void loadAdvisor(GameCharacter character, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Advisor advisor = (Advisor) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("advisor-item.fxml"));
        Pane pane = loader.load();
        rolesBox.getChildren().add(pane);
        AdvisorRoleController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(advisor);
        advisorBox.setSelected(true);
    }

    private void loadPortraits(GameCharacter character) {
        DataPool<String> graphicsPool = ((LoadedData)listEditor.parentController.getObjectPool().get("data")).getGraphicsData();
        DirectSurfaceManager ddsPictures = new DirectSurfaceManager();
        FieldValueMap<String> portraits = character.getPortraits();
        for (String portraitType : portraits.keys()) {
            String portrait = portraits.get(portraitType);
            String filepath = graphicsPool.get(portrait);
            try {
                Image image = ddsPictures.loadDDS(modDirectory() + filepath);
                if (portraitType.equals("small")) {
                    smallPortraitImage.setImage(image);
                    hasSmallPortrait = true;
                } else if (portraitType.equals("large")){
                    bigPortraitImage.setImage(image);
                    hasBigPortrait = true;
                } else {
                    throw new RuntimeException();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.autoSmallPortrait.setDisable(!(hasBigPortrait && !hasSmallPortrait));
    }

    private String modDirectory() {
        return ((FileSearchService)listEditor.parentController.getObjectPool().get("filesearcher")).getModDirectory();
    }
}
