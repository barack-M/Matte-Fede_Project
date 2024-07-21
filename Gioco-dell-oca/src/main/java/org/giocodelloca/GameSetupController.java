package org.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

import org.giocodelloca.effects.*;

public class GameSetupController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private final List<Player> players = new ArrayList<>();
    @FXML
    private GridPane playerGrid;
    @FXML
    private Slider waitOneSlider;
    @FXML
    private Label waitOneLabel;
    @FXML
    private Slider backToOneSlider;
    @FXML
    private Label backToOneLabel;
    private int numP = 0;

    public void initialize() {
        for (int i = 0; i < 2; i++) {
            createP();
            numP++;
        }
        numP--;

        waitOneSlider.valueProperty().addListener((obs, oldVal, newVal) -> waitOneLabel.setText(String.valueOf(newVal.intValue())));
        backToOneSlider.valueProperty().addListener((obs, oldVal, newVal) -> backToOneLabel.setText(String.valueOf(newVal.intValue())));
    }

    public void createP() {
        TextField nameField = new TextField();
        nameField.setPromptText("Nome Giocatore " + (numP + 1));
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.hsb(numP * 100, 0.7, 0.7));

        playerGrid.add(new Label("Giocatore " + (numP + 1)), 0, numP);
        playerGrid.add(nameField, 1, numP);
        playerGrid.add(colorPicker, 2, numP);
    }

    @FXML
    private void setupPlayers() {
        numP++;
        createP();
    }

    @FXML
    private void startGame() {
        players.clear();
        int numRows = playerGrid.getRowCount();

        for (int i = 0; i < numRows; i++) {
            TextField nameField = (TextField) getNodeFromGridPane(playerGrid, 1, i);
            ColorPicker colorPicker = (ColorPicker) getNodeFromGridPane(playerGrid, 2, i);
            assert colorPicker != null;
            if (nameField == null || nameField.getText().isEmpty()) {
                players.add(new Player("P" + i, 0, colorPicker.getValue()));
            } else {
                players.add(new Player(nameField.getText(), 0, colorPicker.getValue()));
            }
        }

        Map<CellEffect, Integer> effectSettings = new HashMap<>();
        effectSettings.put(new WaitOneEffect(), (int) waitOneSlider.getValue());
        effectSettings.put(new BackToOneEffect(), (int) backToOneSlider.getValue());

        Main.setGame(players, effectSettings);
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
