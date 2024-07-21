package org.giocodelloca;

import javafx.scene.image.Image;

public interface CellEffect {
    void apply(Player player, MainController controller);
    Image getImage();
}
