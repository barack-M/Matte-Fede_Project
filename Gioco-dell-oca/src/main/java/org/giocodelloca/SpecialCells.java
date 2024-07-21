package org.giocodelloca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.*;

public class SpecialCells {
    private static MainController controller;

    public static void initialize(Map<CellEffect, Integer> effectSettings) {
        controller = MainController.getInstance();
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
        } else if (player.getPosition() == 62) {
            controller.victory(player);
        } else {
            controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": Nessun effetto");
        }
    }
}
