package org.giocodelloca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
    private static List<Player> players;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        Main.primaryStage = primaryStage;
        showMenu();
    }

    private void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            MenuController controller = loader.getController();
            controller.setStage(primaryStage);
            primaryStage.setTitle("Menu");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            showErrorDialog("Error loading menu", e.getMessage());
        }
    }

    public static void setPlayers(List<Player> players) {
        Main.players = players;
        showGameBoard();
    }

    private static void showGameBoard() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setGame(players);

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