package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.Player;
import org.giocodelloca.MainController;
import org.giocodelloca.CellEffect;

import java.util.Objects;

public class SwapEffect implements CellEffect {

    @Override
    public void apply(Player player, MainController controller) {
        Player minPositionPlayer = null;
        for (Player p : controller.players) {
            if (p != player && (minPositionPlayer == null || p.getPosition() < minPositionPlayer.getPosition())) {
                minPositionPlayer = p;
            }
        }
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": Vieni swappato con il giocatore in ultima posizione (a meno che non sia tu)");

        if (minPositionPlayer != null) {
            int tempPosition = player.getPosition();
            player.movePlayerTo(minPositionPlayer.getPosition());
            minPositionPlayer.movePlayerTo(tempPosition);
        }
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/swap.png")));
    }
}

