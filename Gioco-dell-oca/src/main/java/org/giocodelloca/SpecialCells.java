package org.giocodelloca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.giocodelloca.effects.CellEffect;

import java.util.*;

public class SpecialCells {
    private static GameController controller;

    public static void initialize(Map<CellEffect, Integer> effectSettings) {
        controller = GameController.getInstance();
        if (controller == null) {
            throw new IllegalStateException("MainController is not initialized yet");
        }

        CellEffectManager.configureEffects(effectSettings);

        updateCellIcons();
    }

    private static void updateCellIcons() {
        for (Map.Entry<Integer, CellEffect> entry : CellEffectManager.getAllEffects().entrySet()) {
            int position = entry.getKey();
            CellEffect effect = entry.getValue();
            ImageView imageView = new ImageView();
            Image image = effect.getImage();
            StackPane cell = controller.getCell(position);
            imageView.setImage(image);
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            if (cell != null) {
                cell.getChildren().add(imageView);
            }
        }
    }

    public static void activate(Player player) {
        CellEffect effect = CellEffectManager.getEffect(player.getPosition());
        if (effect != null) {
            effect.apply(player, controller);
        } else {
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": Nessun effetto");
        }
    }
}
