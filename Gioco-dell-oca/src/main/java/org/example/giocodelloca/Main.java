package org.example.giocodelloca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static List<Player> players;
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        showMenu();
    }

    private void showMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
            controller.setPlayers(players);
            controller.setTurn();

            primaryStage.setTitle("Gioco dell'Oca");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

