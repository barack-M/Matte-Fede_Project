package org.giocodelloca.effects;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import org.giocodelloca.GameController;
import org.giocodelloca.Player;

import java.util.Objects;
import java.util.Optional;

public class VictoryEffect implements CellEffect {
    @Override
    public void apply(Player player, GameController controller) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vittoria!");
        alert.setHeaderText(null);
        alert.setContentText(player.getName() + " ha vinto il gioco! Complimenti!");

        ButtonType buttonExit = new ButtonType("Esci dal gioco");
        alert.getButtonTypes().setAll(buttonExit);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonExit) {
            Platform.exit();
        }
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/crown.png")));
    }
}
