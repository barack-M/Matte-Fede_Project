package org.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class PlayerSetupController {

    @FXML
    private GridPane playerGrid;

    private List<Player> players = new ArrayList<>();

    private final boolean chose = false;

    private int numP = 0;

    int numPlayers;

    public void initialize() {
        for(int i = 0; i < 2; i++) {
            createP();
            numP++;
        }
        numP--;
    }

    public void createP(){
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

        for(int i = 0; i < numRows; i++){
            TextField nameField = (TextField) getNodeFromGridPane(playerGrid, 1, i);
            ColorPicker colorPicker = (ColorPicker) getNodeFromGridPane(playerGrid, 2, i);
            if(colorPicker != null) {
                if (nameField.getText().isEmpty()) {
                    players.add(new Player("P" + i, 0, colorPicker.getValue(), null));
                } else {
                    players.add(new Player(nameField.getText(), 0, colorPicker.getValue(), null));
                }
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


