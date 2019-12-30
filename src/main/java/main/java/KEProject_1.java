package main.java;

import java.util.*;

//Ritika was here and so was glenn
public class KEProject_1 {
    public static void main(String[] args) {
        Checkers checkersGame = new Checkers();
        checkersGame.setUpByUser();
        while (checkersGame.isInProgress()) {

            checkersGame.currentPlayerTakeTurn();
            checkersGame.displayBoard();
        }

        checkersGame.displayEndOfGameMessage();
    }
}

class Checkers {
    Scanner input = new Scanner(System.in);
    private Board gameboard;
    private Player playerone;
    private Player playertwo;

    Checkers() {
        gameboard = new Board();
    }

    public Board getGameboard() {
        return gameboard;
    }

    public Player getPlayerone() {
        return playerone;
    }

    public Player getPlayertwo() {
        return playertwo;
    }

    public void setUpByUser() {
        System.out.println("Hello Welcome Player Here is were you choose Red or Black");
        String choose = input.nextLine();
        if (choose.equals("Red")) {
            playerone = new Human("Red");
            playertwo = new Human("Black");
        } else {
            playerone = new Human("Black");
            playertwo = new Human("Red");
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
        System.out.println(gameboard.toString());
    }

    public void displayEndOfGameMessage() {
        System.out.println("You Win");
    }
}

