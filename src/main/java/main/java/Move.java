package main.java;

public class Move {
    private int initialXCoor;
    private int initialYCoor;
    private int endingXCoor;
    private int endingYCoor;

    Move(int initialXCoor, int initialYCoor, int endingXCoor, int endingYCoor) {
        this.initialXCoor = initialXCoor;
        this.initialYCoor = initialYCoor;
        this.endingXCoor = endingXCoor;
        this.endingYCoor = endingYCoor;
    }

    public int getInitialXCoor() {
        return initialXCoor;
    }

    public int getInitialYCoor() {
        return initialYCoor;
    }

    public int getEndingXCoor() {
        return endingXCoor;
    }

    public int getEndingYCoor() {
        return endingYCoor;
    }
}
