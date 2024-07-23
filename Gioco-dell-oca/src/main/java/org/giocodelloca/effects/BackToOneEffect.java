package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.GameController;
import org.giocodelloca.Player;

import java.util.Objects;

public class BackToOneEffect implements CellEffect {
    @Override
    public void apply(Player player, GameController controller) {
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": TORNI ALLA CASELLA UNO: dai " + player.getName() + " che ce la fai!");
        player.movePlayerTo(1);
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/skull.png")));
    }
}