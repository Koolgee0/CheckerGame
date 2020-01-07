package main.java;

import java.util.Scanner;

class Checkers {
    Scanner input = new Scanner(System.in);
    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;
    private int turnToggle = 1;
    private String message;

    Checkers() {
        gameBoard = new Board();
    }

    public void setUpByUser() {
        System.out.println("Welcome player, would you like to play as Red pieces? (y or n)");
        String choose = input.nextLine().toUpperCase();
        if (choose.equals("Y")) {
            playerOne = new Human("Red");
            playerTwo = new Human("Black");
        } else {
            playerOne = new Human("Black");
            playerTwo = new Human("Red");
        }

    }

    public boolean isInProgress() {
        if (gameBoard.getRedCheckerCount() == 0) {
            message = "Black Wins";
            return false;
        } else if (gameBoard.getBlackCheckerCount() == 0) {
            message = "Red Wins";
            return false;
        } else {
            return true;
        }
    }

    public void currentPlayerTakeTurn() {
        if (turnToggle == 1) {
            System.out.println("Player One's Turn (" + playerOne.getColor() + ")");
            Move move = playerOne.makeMove(gameBoard);
            gameBoard.movePiece(move);
        } else {
            System.out.println("Player Two's Turn (" + playerTwo.getColor() + ")");
            Move move = playerTwo.makeMove(gameBoard);
            gameBoard.movePiece(move);
        }
        turnToggle *= -1;
    }

    public void displayBoard() {
        System.out.println(gameBoard.toString());
    }

    public void displayEndOfGameMessage() {
        System.out.println(message);
    }
}
