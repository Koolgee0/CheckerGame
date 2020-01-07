package main.java;

public abstract class Player {

    private String color;

    Player(String color) {
        this.color = color;
    }

    abstract Move makeMove(Board board);

    public String getColor() {
        return color;
    }
}
