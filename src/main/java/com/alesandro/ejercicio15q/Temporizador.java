package com.alesandro.ejercicio15q;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Temporizador extends GridPane {
    @FXML // fx:id="min1"
    private Label min1; // Value injected by FXMLLoader

    @FXML // fx:id="min2"
    private Label min2; // Value injected by FXMLLoader

    @FXML // fx:id="seg1"
    private Label seg1; // Value injected by FXMLLoader

    @FXML // fx:id="seg2"
    private Label seg2; // Value injected by FXMLLoader

    public Temporizador() {
        //
    }
}