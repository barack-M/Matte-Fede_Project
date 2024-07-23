# Gioco dell'Oca

A simple multiplayer board game with customizable game setup options.

## Overview

This project implements a classic "Goose Game" where players move around a board according to dice rolls, encountering special cells with various effects.

## How it Works

### Game Initialization

1. **Game Setup Page**:
    - When the program starts, the Game Setup page is opened along with its controller.
    - The user is prompted to specify the number of cells for each Cell Effect Type and provide player information.

2. **Starting the Game**:
    - When the user presses the `startGame` button, the game initialization begins:
        - Player information and effect settings are collected.
        - The `Main` class's `setGame` method is invoked.
        - The `setGame` method opens the Game page, starts the Game Controller, and initializes the special cells on the board.
        - The `initialize` method of `SpecialCells` calls the `CellEffectManager` to configure effects and then adds the corresponding images on the board.

### Gameplay

- Players take turns pressing the `rollDice` and `endTurn` buttons to play.
- The current player's name, dice result, and cell effect are always displayed as players move around the board.
- The player's methods are invoked to change their position and apply special effects, which are handled by the respective classes in the `effects` package implementing the `CellEffect` interface.
- When a player reaches the last cell, number 62, they win: an alert is shown, and the game closes.

## Internals

### Core Classes and Interfaces

- **Main**: The main application class, handles game setup and transition to the game board.
- **GameSetupController**: Manages the game setup UI and collects user input for players and effects.
- **GameController**: Manages the game board UI, player turns, and game logic.
- **Player**: Manages players data and movement.
- **SpecialCells**: Handles the initialization and activation of special cells.
- **CellEffectManager**: Configures and manages the cell effects on the board.
- **CellEffect**: An interface implemented by various special effect classes in the `effects` package.

### Special Effects

The game includes various special effects such as:
- **WaitOneEffect**: Forces a player to skip their next turn.
- **BackToOneEffect**: Sends a player back to the first cell.
- **SwapEffect**: Swap the player's position with last-player position.
- **RandomEffect**: Applies one of several random effects:
   - Swaps the player with the player in the first position or advances them by 4 positions if they are already in the first position.
   - Makes the player wait for two turns.
   - Moves the player back by 3 positions.
   - Advances the player by 3 positions.
- **VictoryEffect**: Declares victory when a player reaches cell 62.

## Example Flow

1. The game starts and the setup page is displayed.
2. The user inputs the number of special cells and player details.
3. The `startGame` button initializes the game with the provided settings.
4. Players take turns rolling the dice and moving around the board.
5. Special effects are applied based on the cells landed on.
6. The game ends when a player reaches cell 62, displaying a victory alert.