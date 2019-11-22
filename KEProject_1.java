package com.javaacademy.projects.Projects;


public class KEProject_1 {
    public static void main(String[] args) {
        Checker checkersGame = new Checker();
        checkersGame.setUpByUser();
        while (checkersGame.isInProgress()) {

            checkersGame.currentPlayerTakeTurn();
            checkersGame.displayBoard();
        }
        checkersGame.displayEndOfGameMessage();
    }
}

class Checker {
    private Board gameboard;
    private Player playerone;
    private Player playertwo;

    public void setUpByUser() {
    }

    public boolean isInProgress() {

        return false;
    }

    public void currentPlayerTakeTurn() {
    }

    public void displayBoard() {
    }

    public void displayEndOfGameMessage() {
    }
}

abstract class Player {

    private String color;

    Player(String color) {
        this.color = color;


    }

    public Move makeMove(Board board) {
        return null;
    }


}


class Human extends Player {

    Human(String color) {
        super(color);
    }
}

class Computer extends Player {


    Computer(String color) {
        super(color);
    }

}


class Square {

    private Piece piece;


}


class Piece {
    private String color;

    private String rank;

    public void kingPiece() {


    }

}

class Board {
    private Square square;

    public String toString() {

        return " ";

    }

    public boolean isMoveLegal() {
        return false;
    }


    public void movePiece(Move move) {

    }

}


class Move {
    private int initalXCoor;
    private int initalYCoor;
    private int endingXCoor;
    private int endingYCoor;

}