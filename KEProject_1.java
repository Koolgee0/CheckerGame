package com.javaacademy.projects.Projects;


//Ritika was here and so was glenn
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
//Glenn
class Checker {
    private Board gameboard;
    private Player playerone;
    private Player playertwo;

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
//Gelia
abstract class Player {

    private String color;

    Player(String color) {
        this.color = color;


    }

    public Move makeMove(Board board) {
        return null;
    }


}

//All
class Human extends Player {

    Human(String color) {
        super(color);
    }
}
//Glenn
class Computer extends Player {


    Computer(String color) {
        super(color);
    }

}


class Square {

    private Piece piece;


}

//Ritika
class Piece {
    private String color;

    private String rank;

    public void kingPiece() {


    }

}
//Ritika
class Board {
    private Square[][] squares = new Square[8][8];

    public boolean isMoveLegal(Move move) {
        return false;
    }


    public void movePiece(Move move) {

    }

    public String toString() {

        return " ";

    }

}

//Gelia
class Move {
    private int initalXCoor;
    private int initalYCoor;
    private int endingXCoor;
    private int endingYCoor;

}