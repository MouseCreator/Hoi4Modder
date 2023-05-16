package com.example.hoi4modder.controller.command;

import javafx.fxml.Initializable;

public interface ControlConnector {
    void initialize(Initializable initializable);

     Object getFieldByIndex(int index);
     int getIndexOf(Object object);

    int size();
}
