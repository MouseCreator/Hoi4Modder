package com.example.hoi4modder.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

    ActivePaneController currentActive;

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

    private final Map<String, String> configurations = new HashMap<>();

    public String getConfiguration(String key) {
        return configurations.get(key);
    }

}
