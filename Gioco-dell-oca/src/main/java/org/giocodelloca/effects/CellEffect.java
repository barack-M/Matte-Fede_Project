package org.giocodelloca.effects;

import javafx.scene.image.Image;
import org.giocodelloca.GameController;
import org.giocodelloca.Player;

public interface CellEffect {
    void apply(Player player, GameController controller);
    Image getImage();
}
