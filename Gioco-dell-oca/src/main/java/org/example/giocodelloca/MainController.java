package org.example.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.Random;


public class MainController {

    @FXML
    private Button rollButton;

    @FXML
    private Label turnLabel;

    @FXML
    private Label diceResultLabel;

    @FXML
    public static Label cellEffectLabel;

    @FXML
    private GridPane boardGrid;

    private int turn;
    Random random = new Random();
    private List<Player> players;

    public void initialize() {
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        initializePlayersOnBoard();
    }

    private void initializePlayersOnBoard() {
        for (Player player : players) {
            Circle playerToken = new Circle(10, player.getColor());
            boardGrid.add(playerToken, 0, 0);
            player.setToken(playerToken);
        }
    }

    public void setTurn() {
        turn = random.nextInt(players.size());
        turnLabel.setText("Turno di: " + players.get(turn).getName());
    }

    @FXML
    private void rollDice() {
        Player actualPlayer = players.get(turn);

        int roll = random.nextInt(7);
        diceResultLabel.setText("Risultato del dado: " + roll);

        // Logica per muovere i giocatori
        actualPlayer.moveForward(roll);

        if(turn < players.size()) {
            turn += 1;
        } else {
            turn = 1;
        }
    }

    public static String getCellEffect(int position) {
        // Logica per determinare l'effetto della casella
        return "Nessun effetto";
    }
}
