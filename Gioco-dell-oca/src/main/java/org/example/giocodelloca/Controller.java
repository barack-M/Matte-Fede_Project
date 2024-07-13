package org.example.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


public class Controller {

    @FXML
    private Button rollButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Label diceResultLabel;

    @FXML
    private Label cellEffectLabel;

    @FXML
    private GridPane boardGrid;

    @FXML
    private Circle player1;

    @FXML
    private Circle player2;

    private int player1Position = 0;
    private int player2Position = 0;

    public void initialize() {
        // Posiziona inizialmente i giocatori nella prima casella
        movePlayerTo(player1, 0);
        movePlayerTo(player2, 0);
    }

    @FXML
    private void rollDice() {
        int roll = (int)(Math.random() * 6) + 1;
        diceResultLabel.setText("Risultato del dado: " + roll);

        // Logica per muovere i giocatori
        player1Position += roll;
        movePlayerTo(player1, player1Position);

        String effect = getCellEffect(player1Position);  // Metti qui la logica per ottenere l'effetto della casella
        cellEffectLabel.setText("Effetto della casella: " + effect);
    }

    private void movePlayerTo(Circle player, int position) {
        int column = position % 10;
        int row = position / 10;
        GridPane.setColumnIndex(player, column);
        GridPane.setRowIndex(player, row);

        // Offset per distinguere le pedine nella stessa cella
        if (player.equals(player1)) {
            player.setTranslateX(-10);
            player.setTranslateY(-10);
        } else {
            player.setTranslateX(10);
            player.setTranslateY(10);
        }
    }

    private String getCellEffect(int position) {
        // Logica per determinare l'effetto della casella
        // Questo è un esempio, modifica con la tua logica
        return "Nessun effetto";
    }
}
