package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.CellEffect;
import org.giocodelloca.MainController;
import org.giocodelloca.Player;

import java.util.Objects;

public class BackToOneEffect implements CellEffect {
    @Override
    public void apply(Player player, MainController controller) {
        player.movePlayerTo(1);
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": TORNI ALLA CASELLA UNO: dai " + player.getName() + " che ce la fai!");
    }

    @Override
    public Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/giocodelloca/Icons/skull.png")));
    }
}