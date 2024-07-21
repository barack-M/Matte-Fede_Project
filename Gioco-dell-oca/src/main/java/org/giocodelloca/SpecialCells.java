package org.giocodelloca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.*;

public class SpecialCells {
    static MainController controller = MainController.getInstance();

    public static int[] waitOneCord = new int[6];
    public static int[] backtoOneCord = new int[2];
    private static final Map<Integer, Runnable> effects = new HashMap<>();

    public static void initialize(){
        Random random = new Random();
        Set<Integer> uniquePositions = new HashSet<>();

        for(int i = 0; i < 6; i++) {
            int pos;
            do {
                pos = random.nextInt(1, 62);
            } while (!uniquePositions.add(pos));
            waitOneCord[i] = pos;
        }

        for(int i = 0; i < 2; i++) {
            int pos;
            do {
                pos = random.nextInt(30, 55);
            } while (!uniquePositions.add(pos));
            backtoOneCord[i] = pos;
        }

        for (int i = 0; i < 6; i++) {
            addImageToCell(waitOneCord[i], "/org/giocodelloca/Icons/locket.png");
        }
        for (int i = 0; i < 2; i++) {
            addImageToCell(backtoOneCord[i], "/org/giocodelloca/Icons/skull1.png");
        }
        addImageToCell(62, "/org/giocodelloca/Icons/crown.png");
        for (int pos : waitOneCord) {
            effects.put(pos, () -> waitOne(controller.getPlayerAtPosition(pos)));
        }
        for (int pos : backtoOneCord) {
            effects.put(pos, () -> backtoOne(controller.getPlayerAtPosition(pos)));
        }
        effects.put(62, () -> controller.victory(controller.getPlayerAtPosition(62)));
    }

    public static void activate(Player player){
        if (controller == null) {
            return;
        }
        int position = player.getPosition();
        Runnable effect = effects.get(position);
        if (effect != null) {
            effect.run();
        } else {
            controller.cellEffectLabel.setText("Casella " + position + ": Nessun effetto");
        }
    }


    private static void waitOne(Player player){
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": ASPETTA UN TURNO: " + player.getName() +  " per il prossimo turno non ti puoi muovere!");
        player.stuck += 1;
    }

    private static void backtoOne(Player player){
        controller.cellEffectLabel.setText("Casella " + player.getPosition() + ": TORNI ALLA CASELLA UNO: dai " + player.getName() + " che ce la fai!");
        player.movePlayerTo(1);
    }

    private static void addImageToCell(int position, String imagePath) {
        ImageView imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(SpecialCells.class.getResourceAsStream(imagePath)));
        StackPane cell = controller.getCell(position);
        imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        if (cell != null) {
            cell.getChildren().add(imageView);
        }
    }
}