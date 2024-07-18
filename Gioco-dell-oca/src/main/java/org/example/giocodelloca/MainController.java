package org.example.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class MainController {
    @FXML
    private Button rollButton;

    @FXML
    private Label turnLabel;

    @FXML
    private Label diceResultLabel;

    @FXML
    public Label cellEffectLabel;

    @FXML
    private GridPane boardGrid;

    @FXML
    private AnchorPane boardPane;

    private int turn;
    Random random = new Random();
    private List<Player> players;
    private static MainController instance;

    public int[][] snailPath = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8},
            {1, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8}, {6, 8}, {6, 7}, {6, 6}, {6, 5},
            {6, 4}, {6, 3}, {6, 2}, {6, 1}, {6, 0}, {5, 0}, {4, 0}, {3, 0}, {2, 0},
            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 7},
            {3, 7}, {4, 7}, {5, 7}, {5, 6}, {5, 5}, {5, 4}, {5, 3}, {5, 2}, {5, 1},
            {4, 1}, {3, 1}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {3, 6},
            {4, 6}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {3, 2}, {3, 3}, {3, 4}, {3, 5}
    };

    @FXML
    private void initialize() {
        instance = this;
    }

    public static MainController getInstance() {
        return instance;
    }

    public void setGame(List<Player> players) {
        this.players = players;
        initializePlayersOnBoard();
        setSpecial();

        turn = random.nextInt(players.size());
        turnLabel.setText("Turno di: " + players.get(turn).getName());
    }

    private void setSpecial(){
        MainController controller = MainController.getInstance();
        SpecialCells.initialize();
        for(int i = 0; i < 6; i++){
            Rectangle spcell = new Rectangle(100, 100, Color.RED);
            StackPane cell = getCell(SpecialCells.waitCord[i]);
            if (cell != null) {
                cell.getChildren().add(spcell);
            }
        }

        for(int i = 0; i < 2; i++){
            Rectangle spcell = new Rectangle(100, 100, Color.BLUE);
            StackPane cell = getCell(SpecialCells.backtoOneCord[i]);
            if (cell != null) {
                cell.getChildren().add(spcell);
            }
        }
        Rectangle spcell = new Rectangle(100, 100, Color.YELLOW);
        StackPane cell = getCell(62);
        if (cell != null) {
            cell.getChildren().add(spcell);
        }
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

    @FXML
    private void rollDice() {
        Player actualPlayer = players.get(turn);

        int roll = random.nextInt(6) + 1;
        diceResultLabel.setText("Risultato del dado: " + roll);

        actualPlayer.moveForward(roll);

        turn = (turn + 1) % players.size();
        turnLabel.setText("Turno di: " + players.get(turn).getName());
    }

    public String getCellEffect(String string) {
        return string;
    }
}