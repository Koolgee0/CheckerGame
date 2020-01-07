package src.main.java;

class Square {

    private Piece piece;
    private char color;

    Square(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
