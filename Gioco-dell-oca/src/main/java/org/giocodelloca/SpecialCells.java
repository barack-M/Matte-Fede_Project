package org.giocodelloca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class SpecialCells {
    static MainController controller = MainController.getInstance();

    public static int[] waitOneCord = new int[6];
    public static int[] backtoOneCord = new int[2];

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
    }

    public static void activate(Player player){
        if (controller == null) {
            return;
        }
        int position = player.getPosition();

        for (int i : waitOneCord) {
            if (position == i) {
                waitOne(player);
                controller.cellEffectLabel.setText("Casella " + position + ": ASPETTA UN TURNO: " + player.getName() +  " per il prossimo turno non ti puoi muovere!");
                return;
            }
        }
        for (int i : backtoOneCord) {
            if (position == i) {
                controller.cellEffectLabel.setText("Casella " + position + ": TORNI ALLA CASELLA UNO: dai " + player.getName() + " che ce la fai!");
                backtoOne(player);
                return;
            }
        }
        if(position == 62){
            controller.victory(player);
            return;
        }
        controller.cellEffectLabel.setText("Casella " + position + ": Nessun effetto");
    }

    private static void waitOne(Player player){
        player.stuck += 1;
    }

    private static void backtoOne(Player player){
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