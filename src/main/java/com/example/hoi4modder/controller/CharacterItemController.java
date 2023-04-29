package com.example.hoi4modder.controller;

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
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for single character
 */
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

    private final RolePaneControllerPair<CountryLeader> countryLeaderPair = new RolePaneControllerPair<>();
    private final RolePaneControllerPair<NavyLeader> navyLeaderPair = new RolePaneControllerPair<>();
    private final RolePaneControllerPair<UnitLeader> unitLeaderPair = new RolePaneControllerPair<>();
    private final RolePaneControllerPair<Advisor> advisorPair = new RolePaneControllerPair<>();

    public void setParent(CharacterListEditor editor) {
        this.listEditor = editor;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rolesBox.setFillHeight(true);
        countryLeaderBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(countryLeaderBox.isSelected())
                createCountryLeaderPane();
            else
                destructCountryLeaderPane();
        });
    }

    public void fromCharacter(GameCharacter character) {
        LoadedData data = listEditor.parentController.getSavedData().loadedData();
        DataPool<String> localisationPool = data.getLocalisationData();
        characterIDField.setText(character.getIdentification());
        characterNameField.setText(localisationPool.get(character.getName()));
        loadPortraits(character);
        loadRoles(character);
        this.gameCharacter = character;
    }

    private void loadRoles(GameCharacter character) {
        for (String role : character.getRoles().keys()) {
            switch (role) {
                case "advisor" -> loadAdvisor(character, role);
                case "country_leader" -> loadCountryLeader(character);
                case "corps_commander", "field_marshal" -> loadUnitLeader(character, role);
                case "navy_leader" -> loadNavyLeader(character, role);
            }
        }
    }

    private void loadNavyLeader(GameCharacter character, String role) {
        FXMLLoader loader = new FXMLLoader();
        NavyLeader navyLeader = (NavyLeader) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("navy-leader-item.fxml"));
        Pane pane;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        rolesBox.getChildren().add(pane);
        NavyLeaderController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(navyLeader);
        navyLeaderBox.setSelected(true);

    }

    private void loadUnitLeader(GameCharacter character, String role) {
        FXMLLoader loader = new FXMLLoader();
        UnitLeader unitLeader = (UnitLeader) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("unit-leader-item.fxml"));
        Pane pane;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        rolesBox.getChildren().add(pane);
        UnitLeaderController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(unitLeader);
        unitLeaderBox.setSelected(true);
    }

    private void createCountryLeaderPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("country-leader-item.fxml"));
        try {
            Pane pane = loader.load();
            CountryLeaderRoleController controller = loader.getController();
            countryLeaderPair.update(pane, controller);
            controller.setParent(this);
            rolesBox.getChildren().add(0, pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void destructCountryLeaderPane() {
        if (countryLeaderPair.isFilled()) {
            rolesBox.getChildren().remove(countryLeaderPair.getPane());
            countryLeaderPair.clear();
        }
    }
    private void loadCountryLeader(GameCharacter character) {
        CountryLeader countryLeader = (CountryLeader) character.getRoles().get(CharacterRoles.COUNTRY_LEADER);
        countryLeaderBox.setSelected(true);
        countryLeaderPair.getController().fromRole(countryLeader);
    }

    private void loadAdvisor(GameCharacter character, String role) {
        FXMLLoader loader = new FXMLLoader();
        Advisor advisor = (Advisor) character.getRoles().get(role);
        loader.setLocation(getClass().getResource("advisor-item.fxml"));
        Pane pane;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        rolesBox.getChildren().add(pane);
        AdvisorRoleController controller = loader.getController();
        controller.setParent(this);
        controller.fromRole(advisor);
        advisorBox.setSelected(true);
    }

    private void loadPortraits(GameCharacter character) {
        LoadedData data = listEditor.parentController.getSavedData().loadedData();
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
        FileSearchService service = listEditor.parentController.getSavedData().fileSearchService();
        LoadedData data =  listEditor.parentController.getSavedData().loadedData();
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
        Window stage = listEditor.parentController.getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setLargePortrait(file);
            this.hasBigPortrait = true;
            setAutoButton();
        }
    }
    private void setLargePortrait(File file) {
        ImageTransformer transformer = new ImageTransformer();

        FileSearchService service = listEditor.parentController.getSavedData().fileSearchService();
        LoadedData data = listEditor.parentController.getSavedData().loadedData();
        DirectSurfaceManager ddsImage = new DirectSurfaceManager();
        String filename = "Portrait_" + gameCharacter.getIdentification() + ".dds";
        String destination = service.getModDirectory() + Destinations.get().leaderGFX(listEditor.getCountryTag());
        String fullNewPath = destination + filename;
        String pathToPut = Destinations.get().leaderGFX(listEditor.getCountryTag()) + filename;
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

        FileSearchService service = listEditor.parentController.getSavedData().fileSearchService();
        LoadedData data = listEditor.parentController.getSavedData().loadedData();
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
        Window stage = listEditor.parentController.getWindow();
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
