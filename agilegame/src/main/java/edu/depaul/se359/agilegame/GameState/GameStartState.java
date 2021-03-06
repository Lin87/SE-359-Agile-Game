package edu.depaul.se359.agilegame.GameState;

/*
    The game start state class. Carry out actions that will happen during game start state.
    Starts initial game loop for user inputs.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

public class GameStartState implements GameState {

    @Override
    public void saveState(GameStateContext ctx) {

        ctx.setState(this);
        this.startGame();

    }

    private void startGame() {

        GameManager.getInstance().playGame();

    }

    @Override
    public String toString() {
        return "Start";
    }

}
