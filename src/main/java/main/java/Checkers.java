package main.java;

import java.util.Scanner;

class Checkers {
    Scanner input = new Scanner(System.in);
    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;

    Checkers() {
        gameBoard = new Board();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setUpByUser() {
        System.out.println("Hello Welcome Player Here is were you choose Red or Black");
        String choose = input.nextLine();
        if (choose.equals("Red")) {
            playerOne = new Human("Red");
            playerTwo = new Human("Black");
        } else {
            playerOne = new Human("Black");
            playerTwo = new Human("Red");
        }

    }

    public boolean isInProgress() {
        return true;
    }

    public void currentPlayerTakeTurn() {
        while (true) {
            String playerInput = input.nextLine();
            break;
        }
    }

    public void displayBoard() {
        System.out.println(gameBoard.toString());
    }

    public void displayEndOfGameMessage() {
        System.out.println("You Win");
    }
}
