package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.character_extra.CharacterInfo;
import com.example.hoi4modder.controller.character_extra.RoleSwitcher;
import com.example.hoi4modder.controller.character_extra.RoleSwitcherBuilder;
import com.example.hoi4modder.controller.requests.ItemPresentRequest;
import com.example.hoi4modder.game.FieldValueMap;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.images.DirectSurfaceManager;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.AppConfig;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ImageTransformer;
import com.example.hoi4modder.service.SavedDataContainer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for single character
 */
public class CharacterItemController implements Initializable, ListItemController<GameCharacter> {
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

    private ItemContainer<GameCharacter> listEditor;
    private final CharacterInfo characterInfo;
    private boolean hasBigPortrait = false;
    private boolean hasSmallPortrait = false;
    private String countryTag;
    private GameCharacter gameCharacter;
    private final RoleSwitcher<CountryLeader> countryLeaderRoleSwitcher;
    private final RoleSwitcher<NavyLeader> navyLeaderRoleSwitcher;
    private final RoleSwitcher<UnitLeader> unitLeaderRoleSwitcher;
    private final RoleSwitcher<Advisor> advisorRoleSwitcher;

    public CharacterInfo getCharacterInfo() {
        return characterInfo;
    }
    public CharacterItemController() {
        characterInfo = new CharacterInfo();
        RoleSwitcherBuilder builder = new RoleSwitcherBuilder();
        countryLeaderRoleSwitcher = builder.buildCountryLeaderSwitcher(this);
        navyLeaderRoleSwitcher = builder.buildNavyLeaderSwitcher(this);
        unitLeaderRoleSwitcher = builder.buildUnitLeaderSwitcher(this);
        advisorRoleSwitcher = builder.buildAdvisorSwitcher(this);
    }

    public void setParent(ItemContainer<GameCharacter> editor) {
        this.listEditor = editor;
        countryTag = listEditor.getCountry().getTag();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rolesBox.setFillHeight(true);

    }
    @Override
    public void fromModel(GameCharacter character) {
        this.gameCharacter = character;
        initRoles(gameCharacter);
        LoadedData data = SavedDataContainer.get().loadedData();
        characterIDField.setText(character.getIdentification());
        if (!character.getName().isEmpty()) {
            DataPool<String> localisationPool = data.getLocalisationData();
            try {
                characterNameField.setText(localisationPool.get(character.getName()));
            } catch (Exception e) {
                characterNameField.setText(character.getIdentification());
            }
        }
        loadPortraits(character);
        loadRoles(character);
        setValueListeners();
    }

    @Override
    public GameCharacter toModel() {
        return gameCharacter;
    }
    private void setValueListeners() {
        characterIDField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            gameCharacter.setIdentification(newValue);
            ItemPresentRequest request = new ItemPresentRequest(gameCharacter.getIdentification());
            listEditor.handle(request);
            if (request.getIsPresent())
                characterIDField.setStyle("-fx-control-inner-background: #FFFFFF");
            else {
                characterIDField.setStyle("-fx-control-inner-background: #FFE3E3");
            }
        });
    }
    private void initRoles(GameCharacter character) {
        countryLeaderRoleSwitcher.bindCheckBox(rolesBox, countryLeaderBox, character);
        unitLeaderRoleSwitcher.bindCheckBox(rolesBox, unitLeaderBox, character);
        navyLeaderRoleSwitcher.bindCheckBox(rolesBox, navyLeaderBox, character);
        advisorRoleSwitcher.bindCheckBox(rolesBox, advisorBox, character);
    }

    private void loadRoles(GameCharacter character) {
        for (String role : character.getRoles().keys()) {
            switch (role) {
                case CharacterRoles.ADVISOR -> loadAdvisor(character);
                case CharacterRoles.COUNTRY_LEADER -> loadCountryLeader(character);
                case CharacterRoles.UNIT_LEADER -> loadUnitLeader(character);
                case CharacterRoles.NAVY_LEADER -> loadNavyLeader(character);
            }
        }
    }

    private void loadNavyLeader(GameCharacter character) {
        NavyLeader navyLeader = (NavyLeader) character.getRoles().get(CharacterRoles.NAVY_LEADER);
        navyLeaderBox.setSelected(true);
        navyLeaderRoleSwitcher.getController().fromRole(navyLeader);
    }

    private void loadUnitLeader(GameCharacter character) {
        UnitLeader unitLeader = (UnitLeader) character.getRoles().get(CharacterRoles.UNIT_LEADER);
        unitLeaderBox.setSelected(true);
        unitLeaderRoleSwitcher.getController().fromRole(unitLeader);
    }

    private void loadCountryLeader(GameCharacter character) {
        CountryLeader countryLeader = (CountryLeader) character.getRoles().get(CharacterRoles.COUNTRY_LEADER);
        countryLeaderBox.setSelected(true);
        countryLeaderRoleSwitcher.getController().fromRole(countryLeader);
    }

    private void loadAdvisor(GameCharacter character) {
        Advisor advisor = (Advisor) character.getRoles().get(CharacterRoles.ADVISOR);
        advisorBox.setSelected(true);
        advisorRoleSwitcher.getController().fromRole(advisor);
    }

    private void loadPortraits(GameCharacter character) {
        LoadedData data = SavedDataContainer.get().loadedData();
        DataPool<String> graphicsPool = data.getGraphicsData();
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
                e.printStackTrace();
            }
        }
        setAutoButton();
    }

    private void setAutoButton() {
        this.autoSmallPortrait.setDisable(!(hasBigPortrait && !hasSmallPortrait));
    }

    private String modDirectory() {
        return new AppConfig().getModDirectory();
    }

    @FXML
    void toSmallPortrait() {
        ImageTransformer transformer = new ImageTransformer();
        FileSearchService service = SavedDataContainer.get().fileSearchService();
        LoadedData data =  SavedDataContainer.get().loadedData();
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
    }

    @FXML
    void changeLarge() {
        FileChooser fileChooser = initFileChooser();
        Window stage = listEditor.getMainController().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setLargePortrait(file);
            this.hasBigPortrait = true;
            setAutoButton();
        }
    }
    private void setLargePortrait(File file) {
        ImageTransformer transformer = new ImageTransformer();
        FileSearchService service = SavedDataContainer.get().fileSearchService();
        LoadedData data =SavedDataContainer.get().loadedData();
        DirectSurfaceManager ddsImage = new DirectSurfaceManager();
        String filename = "Portrait_" + gameCharacter.getIdentification() + ".dds";
        String destination = service.getModDirectory() + Destinations.get().leaderGFX(countryTag);
        String fullNewPath = destination + filename;
        String pathToPut = Destinations.get().leaderGFX(countryTag) + filename;
        String key = "GFX_Portrait_" + gameCharacter.getIdentification();
        transformer.toBigImage(file, fullNewPath);
        gameCharacter.getPortraits().put("large", key);
        data.getGraphicsData().put("leader", key, pathToPut);
        try {
            bigPortraitImage.setImage(ddsImage.loadDDS(fullNewPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private FileChooser initFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image","*.png", ".jpg", ".dds", ".bmp"));
        return fileChooser;
    }
    private void setSmallPortrait(File file) {
        ImageTransformer transformer = new ImageTransformer();

        FileSearchService service = SavedDataContainer.get().fileSearchService();
        LoadedData data = SavedDataContainer.get().loadedData();
        DirectSurfaceManager ddsImage = new DirectSurfaceManager();
        String filename = "idea_" + gameCharacter.getIdentification() + ".dds";
        String destination = service.getModDirectory() + Destinations.get().ideasGFX();
        String fullNewPath = destination + filename;
        String pathToPut = Destinations.get().ideasGFX() + filename;
        String key = "GFX_idea_" + gameCharacter.getIdentification();
        transformer.toSmallImage(file, fullNewPath);
        gameCharacter.getPortraits().put("small", key);
        data.getGraphicsData().put("ideas_characters", key, pathToPut);
        try {
            smallPortraitImage.setImage(ddsImage.loadDDS(fullNewPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void resetLargeImage() {
        bigPortraitImage.setImage(new Image(new File(Destinations.get().noLargePortrait()).toURI().toString()));
        this.hasSmallPortrait = false;
    }
    private void resetSmallImage() {
        smallPortraitImage.setImage(new Image(new File(Destinations.get().noSmallPortrait()).toURI().toString()));
        this.hasSmallPortrait = false;
    }
    @FXML
    void changeSmall() {
        FileChooser fileChooser = initFileChooser();
        Window stage = listEditor.getMainController().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setSmallPortrait(file);
            this.hasSmallPortrait = true;
            setAutoButton();
        }
    }
    @FXML
    void removeLarge() {
        resetLargeImage();
        gameCharacter.getPortraits().remove("large");
        setAutoButton();
    }

    @FXML
    void removeSmall() {
        resetSmallImage();
        gameCharacter.getPortraits().remove("small");
        setAutoButton();
    }
}
