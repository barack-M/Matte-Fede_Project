package org.giocodelloca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private static List<Player> players;
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
            GameSetupController controller = loader.getController();
            controller.setStage(primaryStage);
            primaryStage.setTitle("Game Setup");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            showErrorDialog("Error loading game setup", e.getMessage());
        }
    }

    public static void setGame(List<Player> players, Map<CellEffect, Integer> effectSettings) {
        Main.players = players;

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setGame(players);
            SpecialCells.initialize(effectSettings);

            primaryStage.setTitle("Gioco dell'Oca");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            showErrorDialog("Error loading game board", e.getMessage());
        }
    }

    private static void showGameBoard() {

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