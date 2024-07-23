package org.giocodelloca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.giocodelloca.effects.CellEffect;

import java.util.List;
import java.util.Map;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        Main.primaryStage = primaryStage;
        showGameSetup();
    }

    private void showGameSetup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-setup.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Game Setup");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            showErrorDialog("Error loading game setup", e.getMessage());
        }
    }

    public static void setGame(List<Player> players, Map<CellEffect, Integer> effectSettings) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.setGame(players);
            SpecialCells.initialize(effectSettings);

            primaryStage.setTitle("Gioco dell'Oca");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            showErrorDialog("Error loading game board", e.getMessage());
        }
    }

    private static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}