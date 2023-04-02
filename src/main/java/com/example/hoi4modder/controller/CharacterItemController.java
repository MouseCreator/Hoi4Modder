package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.FieldValueMap;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.model.files.images.DirectSurfaceManager;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    }

    private void loadPortraits(GameCharacter character) {
        DataPool<String> graphicsPool = ((LoadedData)listEditor.parentController.getObjectPool().get("data")).getGraphicsData();
        DirectSurfaceManager ddsPictures = new DirectSurfaceManager();
        FieldValueMap<String> portraits = character.getPortraits();
        for (String portraitType : portraits.keys()) {
            String portrait = portraits.get(portraitType);
            String filepath = graphicsPool.get(portrait);
            try {
                Image image = ddsPictures.loadDDS("C:\\Users\\mysha\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\leylamod12d\\" + filepath);
                if (portraitType.equals("small")) {
                    smallPortraitImage.setImage(image);
                } else if (portraitType.equals("large")){
                    bigPortraitImage.setImage(image);
                } else {
                    throw new RuntimeException();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
