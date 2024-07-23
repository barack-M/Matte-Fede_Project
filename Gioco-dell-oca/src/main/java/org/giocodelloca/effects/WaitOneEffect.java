package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.GameController;
import org.giocodelloca.Player;

import java.util.Objects;

public class WaitOneEffect implements CellEffect {
    @Override
    public void apply(Player player, GameController controller) {
        player.stuck += 1;
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": ASPETTA UN TURNO: " + player.getName() + " per il prossimo turno non ti puoi muovere!");
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/locket.png")));
    }
}