package org.example.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PlayerSetupController {
    @FXML
    private TextField numPlayersField;
    @FXML
    private GridPane playerGrid;

    private List<Player> players = new ArrayList<>();

    int numPlayers;

    @FXML
    private void setupPlayers() {
        playerGrid.getChildren().clear();
        try {
            numPlayers = Integer.parseInt(numPlayersField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        for (int i = 0; i < numPlayers; i++) {
            TextField nameField = new TextField();
            nameField.setPromptText("Nome Giocatore " + (i + 1));
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setValue(Color.BLACK);

            playerGrid.add(new Label("Giocatore " + (i + 1)), 0, i);
            playerGrid.add(nameField, 1, i);
            playerGrid.add(colorPicker, 2, i);
        }
    }

    @FXML
    private void startGame() {
        players.clear();
        int numRows = playerGrid.getRowCount();

        for(int i = 0; i < numRows; i++){
            TextField nameField = (TextField) getNodeFromGridPane(playerGrid, 1, i);
            ColorPicker colorPicker = (ColorPicker) getNodeFromGridPane(playerGrid, 2, i);

            if (nameField != null && colorPicker != null) {
                players.add(new Player(nameField.getText(), 0, colorPicker.getValue(), null));
            }
        }
        Main.setPlayers(players);
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


