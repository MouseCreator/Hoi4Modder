package com.example.hoi4modder.controller;
import com.example.hoi4modder.service.AppConfig;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.SavedData;
import com.example.hoi4modder.service.SavedDataFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the main window and scene
 */
public class MainController implements Initializable {

    @FXML
    private Pane leftContent;

    @FXML
    private Pane mainContent;

    @FXML
    private MenuBar mainMenu;

    @FXML
    private TextArea consoleView;

    @FXML
    private ScrollPane mainPane;

    @FXML
    private Pane rightContent;

    @FXML
    private ScrollPane sideButton;

    @FXML
    private ScrollPane sideTop;

    private ActivePaneController currentActive;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadPage(new CharacterListEditor().load(this));
    }
    private void loadScene() {
        mainContent.getChildren().clear();
        mainContent.getChildren().add(currentActive.getContent());
    }

    /**
     * Changes workspace pane to new page
     * @param toLoad - page to open. Must be initialized.
     */
    public void loadPage(ActivePaneController toLoad) {
        if (closeActiveController())
            loadNext(toLoad);

    }

    private boolean closeActiveController() {
        if (currentActive == null) {
            return true;
        }
        return(currentActive.onCloseRequest());
    }

    private void loadNext(ActivePaneController key) {
        if (currentActive != null)
            currentActive.onClose();
        currentActive = key;
        loadScene();
        currentActive.initContent();
    }
    public void report(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: red");
    }
    public void inform(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: black");
    }
    public void warn(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: orange");
    }
    public void congratulate(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: green");
    }

    private final SavedData savedData;

    public MainController() {
        savedData = SavedDataFactory.factory().getSavedData();
    }
    public SavedData getSavedData() {
        return savedData;
    }

    public Window getWindow() {
        return mainPane.getScene().getWindow();
    }

    @FXML
    void runGame() {
        AppConfig appConfig = new AppConfig();
        String gameDir = appConfig.getGameDirectory();
        String gameExe = Destinations.get().gamePath(gameDir);
        System.out.println(gameExe);
        try {
            Runtime.getRuntime().exec(gameExe, null, new File(gameDir));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
