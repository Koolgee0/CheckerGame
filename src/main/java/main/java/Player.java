package main.java;

public abstract class Player {

    private String color;

    Player(String color) {
        this.color = color;


    }

    public Move makeMove(Board board) {
        return null;
    }


}
