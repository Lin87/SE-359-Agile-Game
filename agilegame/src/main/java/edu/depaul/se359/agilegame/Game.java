package edu.depaul.se359.agilegame;

/*
    The application's starting point (a.k.a., the main class)
 */

import edu.depaul.se359.agilegame.GameState.GameManager;

public class Game {

    public static void main(String[] args) {

        // start the game when program runs
        GameManager.getInstance().startGame();

    }

}
