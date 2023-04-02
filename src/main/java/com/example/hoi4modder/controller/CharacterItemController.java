package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.FieldValueMap;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.images.DirectSurfaceManager;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ImageTransformer;
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

    private GameCharacter gameCharacter;

    public void setParent(CharacterListEditor editor) {
        this.listEditor = editor;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void fromCharacter(GameCharacter character) {
        LoadedData data = (LoadedData)listEditor.parentController.getObjectPool().extract("data");
        DataPool<String> localisationPool = data.getLocalisationData();
        listEditor.parentController.getObjectPool().put("data", data);
        characterIDField.setText(character.getIdentification());
        characterNameField.setText(localisationPool.get(character.getName()));
        loadPortraits(character);
        loadRoles(character);
        this.gameCharacter = character;
    }

    private void loadRoles(GameCharacter character) {
        try {
            for (String role : character.getRoles().keys()) {
                switch (role) {
                    case "advisor" -> loadAdvisor(character, role);
                    case "country_leader" -> loadCountryLeader(character, role);
                    case "corps_commander", "field_marshal" -> loadUnitLeader(character, role);
                    case "navy_leader" -> loadNavyLeader(character, role);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNavyLeader(GameCharacter character, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        NavyLeader navyLeader = (NavyLeader) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("navy-leader-item.fxml"));
        Pane pane = loader.load();
        rolesBox.getChildren().add(pane);
        NavyLeaderController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(navyLeader);
        navyLeaderBox.setSelected(true);

    }

    private void loadUnitLeader(GameCharacter character, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        UnitLeader unitLeader = (UnitLeader) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("unit-leader-item.fxml"));
        Pane pane =  loader.load();
        rolesBox.getChildren().add(pane);
        UnitLeaderController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(unitLeader);
        unitLeaderBox.setSelected(true);
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
        LoadedData data = (LoadedData)listEditor.parentController.getObjectPool().extract("data");
        DataPool<String> graphicsPool = data.getGraphicsData();
        listEditor.parentController.getObjectPool().put("data", data);
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
        FileSearchService service = (FileSearchService)listEditor.parentController.getObjectPool().extract("filesearcher");
        String modDir = service.getModDirectory();
        listEditor.parentController.getObjectPool().put("filesearcher", service);
        return modDir;
    }

    @FXML
    void toSmallPortrait() {
        ImageTransformer transformer = new ImageTransformer();
        FileSearchService service = (FileSearchService)listEditor.parentController.getObjectPool().extract("filesearcher");
        LoadedData data = (LoadedData) listEditor.parentController.getObjectPool().extract("data");
        DataPool<String> graphicsData = data.getGraphicsData();
        DirectSurfaceManager ddsImage = new DirectSurfaceManager();


        String source = service.getModDirectory() + graphicsData.get(gameCharacter.getPortraits().get("large"));
        String frame =  Destinations.get().frameImage();
        String filename = "idea_" + gameCharacter.getIdentification();
        String destination = service.getModDirectory() + Destinations.get().ideasGFX() + filename + ".dds";
        String key = "GFX_" + filename;
        transformer.toPortrait(source,frame,destination);
        graphicsData.put("ideas_characters", key, Destinations.get().ideasGFX() + filename + ".dds");
        gameCharacter.getPortraits().put("small", key);
        try {
            smallPortraitImage.setImage(ddsImage.loadDDS(destination));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listEditor.parentController.getObjectPool().put("filesearcher", service);
        listEditor.parentController.getObjectPool().put("data", data);
    }
}
