package org.example.giocodelloca;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void startNewGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("player-setup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Imposta Giocatori");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyRules() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rules.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modifica Regole");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
