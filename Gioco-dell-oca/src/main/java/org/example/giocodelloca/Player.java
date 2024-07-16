package org.example.giocodelloca;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    public String name;
    public int position;
    public Color color;
    public Circle Token;

    public Player(String name, int position, Color color, Circle Token) {
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

    public Color getColor() {
        return color;
    }

    public Circle getToken() {
        return Token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setToken(Circle token) {
        Token = token;
    }

    public void moveForward(int steps) {
        movePlayerTo(getPosition() + steps);
    }

    public void movePlayerTo(int newPosition) {
        MainController controller = MainController.getInstance();
        if (controller == null) {
            return;
        }

        if(newPosition == 63) {
            controller.victory(this);
        } else if (newPosition > 63) {
            int overflow = newPosition - 63;
            newPosition = 63 - overflow;
        }

        StackPane cell = controller.getCell(position);
        if(cell != null) {
            cell.getChildren().remove(getToken());
        }

        this.position = newPosition;

        cell = controller.getCell(position);
        if(cell != null) {
            int offset = cell.getChildren().size() * 15;
            getToken().setTranslateX(offset);
            getToken().setTranslateY(offset);
            cell.getChildren().add(getToken());
        }

        String effect = controller.getCellEffect(position);
        controller.cellEffectLabel.setText("Effetto della casella: " + effect);
    }
}