package com.example.hoi4modder.controller;
import com.example.hoi4modder.controller.multithreading.RunGameTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

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
        Platform.runLater(this::initWindow);
    }
    private Window window;
    private void initWindow() {
        this.window = mainPane.getScene().getWindow();
        window.setOnCloseRequest(windowEvent -> {
            if (currentActive != null) {
                currentActive.onClose();
            }
        });
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


    public MainController() {

    }

    public Window getWindow() {
        return window;
    }

    @FXML
    void runGame() {
        RunGameTask runGameTask = new RunGameTask();
        try {
            runGameTask.call();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to start hoi4.exe; " +
                    "Make sure that location to game is set properly and hoi4.exe is in the specified directory!");
            alert.showAndWait();
        }
    }

    public Scene getScene() {
        if (mainPane == null) {
            throw new IllegalStateException("Main pain is not initialized!");
        }
        Scene scene = mainPane.getScene();
        if (scene == null) {
            throw new IllegalStateException("Scene is not assigned to main pane, but is tried to be accessed");
        }
        return scene;
    }
}
