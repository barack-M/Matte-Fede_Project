package org.example.giocodelloca;

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
        movePlayerTo(position + steps);
    }

    public void movePlayerTo(int newPosition) {
        this.position = newPosition;

        String effect = MainController.getCellEffect(position);
        MainController.cellEffectLabel.setText("Effetto della casella: " + effect);
    }
}
