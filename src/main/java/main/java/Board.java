package src.main.java;


public class Board {
    public static String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
    private Square[][] squares = new Square[8][8];

    Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = (i + j) % 2 == 0 ? new Square('\u25A0') : new Square('\u25A1');
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean isMoveLegal(Move move) {
        return false;
    }


    public void movePiece(Move move) {

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("  1 2 3 4 5 6 7 8");
        builder.append("\n");
        for (int i = 0; i < 8; i++) {
            builder.append(letters[i]).append(" ");
            for (int j = 0; j < 8; j++) {
                builder.append(squares[i][j].getColor()).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}