package main.java;

public class KEProject_1 {
    public static void main(String[] args) {
        Checkers checkersGame = new Checkers();
        checkersGame.setUpByUser();
        while (checkersGame.isInProgress()) {
            checkersGame.displayBoard();
            checkersGame.currentPlayerTakeTurn();
        }
        checkersGame.displayEndOfGameMessage();
    }
}
