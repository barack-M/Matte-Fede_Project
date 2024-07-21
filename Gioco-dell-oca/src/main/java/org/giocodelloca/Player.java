package org.giocodelloca;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player {
    public String name;
    public int position;
    public Color color;
    public Circle Token;
    public int stuck = 0;

    public Player(String name, int position, Color color) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.Token = new Circle();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Circle getToken() {
        return Token;
    }

    public void setToken(Circle token) {
        Token = token;
    }

    public void movePlayerTo(int newPosition) {
        MainController controller = MainController.getInstance();
        if (controller == null) {
            return;
        }

        if (newPosition > 62) {
            int overflow = newPosition - 62;
            newPosition = 62 - overflow;
        }

        StackPane cell = controller.getCell(getPosition());
        if (cell != null) {
            cell.getChildren().remove(getToken());
        }

        setPosition(newPosition);
        cell = controller.getCell(getPosition());
        if (cell != null) {
            int playerIndex = cell.getChildren().size();

            double[][] offsets = {
                    {-20, -20}, {20, -20}, {-20, 20}, {20, 20},
                    {0, -25}, {25, 0}, {0, 25}, {-25, 0}
            };

            double offsetX = offsets[playerIndex % offsets.length][0];
            double offsetY = offsets[playerIndex % offsets.length][1];

            getToken().setTranslateX(offsetX);
            getToken().setTranslateY(offsetY);
            cell.getChildren().add(getToken());
        }
    }
}