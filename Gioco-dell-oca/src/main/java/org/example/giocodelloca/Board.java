package org.example.giocodelloca;

public class Board {

    private int currentPlayerPosition;

    public Board() {
        currentPlayerPosition = 0;
    }

    public void movePlayer(int steps) {
        currentPlayerPosition += steps;
        // Logica per gestire caselle speciali, vincita, ecc.
    }
}

