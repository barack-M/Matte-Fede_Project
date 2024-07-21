package org.giocodelloca;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MainController {
    private static MainController instance;
    @FXML
    public Label cellEffectLabel;
    public int[][] snailPath = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8},
            {1, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8}, {6, 8}, {6, 7}, {6, 6}, {6, 5},
            {6, 4}, {6, 3}, {6, 2}, {6, 1}, {6, 0}, {5, 0}, {4, 0}, {3, 0}, {2, 0},
            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 7},
            {3, 7}, {4, 7}, {5, 7}, {5, 6}, {5, 5}, {5, 4}, {5, 3}, {5, 2}, {5, 1},
            {4, 1}, {3, 1}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {3, 6},
            {4, 6}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {3, 2}, {3, 3}, {3, 4}, {3, 5}
    };
    Random random = new Random();
    @FXML
    private Button rollButton;
    @FXML
    private Button nextTurnButton;
    @FXML
    private Label turnLabel;
    @FXML
    private Label diceResultLabel;
    @FXML
    private GridPane boardGrid;
    private int turn;
    private List<Player> players;

    public static MainController getInstance() {
        return instance;
    }

    @FXML
    private void initialize() {
        instance = this;
    }

    public void setGame(List<Player> players) {
        this.players = players;
        initializePlayersOnBoard();
        ObservableList<Node> children = boardGrid.getChildren();
        for (Node child : children) {
            if (child instanceof Parent parentNode) {
                parentNode.getChildrenUnmodifiable().getFirst().setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold");
            }
            child.setStyle("-fx-border-color: rgb(101, 67, 33); -fx-border-width: 2;");
        }

        turn = random.nextInt(players.size());
        turnLabel.setText("Turno di: " + players.get(turn).getName());
        nextTurnButton.setDisable(true);
    }

    private void initializePlayersOnBoard() {
        for (Player player : players) {
            Circle playerToken = new Circle(10, player.getColor());
            player.setToken(playerToken);
            StackPane cell = getCell(0);
            if (cell != null) {
                cell.getChildren().add(playerToken);
            }
        }
    }

    public StackPane getCell(int pos) {
        int[] coord = snailPath[pos];
        for (Node node : boardGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == coord[0] && GridPane.getColumnIndex(node) == coord[1]) {
                return (StackPane) node;
            }
        }
        return null;
    }

    public Player getPlayerAtPosition(int position) {
        for (Player player : players) {
            if (player.getPosition() == position) {
                return player;
            }
        }
        return null;
    }

    @FXML
    private void rollDice(){
        Player actualPlayer = players.get(turn);

        int roll = random.nextInt(6) + 1;
        diceResultLabel.setText("Risultato del dado: " + roll);
        actualPlayer.movePlayerTo(actualPlayer.getPosition() + roll);
        SpecialCells.activate(actualPlayer);
        rollButton.setDisable(true);
        nextTurnButton.setDisable(false);
    }

    @FXML
    private void nextTurn(){
        turn = (turn + 1) % players.size();
        Player nextPlayer = players.get(turn);
        turnLabel.setText("Turno di: " + nextPlayer.getName());
        diceResultLabel.setText("Risultato del dado:");
        if (nextPlayer.stuck == 0) {
            rollButton.setDisable(false);
            nextTurnButton.setDisable(true);
            cellEffectLabel.setText("");
        } else {
            nextPlayer.stuck--;
            diceResultLabel.setText("");
            cellEffectLabel.setText("Casella " + nextPlayer.getPosition() + ": salti questo turno");
        }
    }

    public void victory(Player winner){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vittoria!");
        alert.setHeaderText(null);
        alert.setContentText(winner.getName() + " ha vinto il gioco! Complimenti!");

        ButtonType buttonExit = new ButtonType("Esci dal gioco");
        alert.getButtonTypes().setAll(buttonExit);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonExit) {
            Platform.exit();
        }
    }
}