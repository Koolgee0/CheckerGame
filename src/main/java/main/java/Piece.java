package main.java;

public class Piece {
    public static String PAWN_RANK = "Pawn";
    public static String KING_RANK = "King";

    private String color;

    private String rank;

    Piece(String color) {
        this.color = color;
        rank = PAWN_RANK;
    }

    public String getColor() {
        return color;
    }

    public String getRank() {
        return rank;
    }

    public void kingPiece() {
        rank = KING_RANK;
    }

    public String toString() {
        if (color.equals("Red") && rank.equals(PAWN_RANK)) {
            return "r ";
        }
        if (color.equals("Black") && rank.equals(PAWN_RANK)) {
            return "b ";
        }
        if (color.equals("Red") && rank.equals(KING_RANK)) {
            return "R ";
        }
        if (color.equals("Black") && rank.equals(KING_RANK)) {
            return "B ";
        }
        return null;
    }
}
