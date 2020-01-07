package main.java;


import java.util.HashMap;

public class Board {
    public static char RED = '\u25A0';
    public static char BLACK = '\u25A1';
    public static String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

    private Square[][] squares = new Square[8][8];
    private HashMap<String, Coordinate> coordinates = new HashMap<>();
    private int blackCheckerCount;
    private int redCheckerCount;

    Board() {
        placeTiles();
        placeBlackCheckers();
        placeRedCheckers();
        initializeCoordinates();
        blackCheckerCount = 12;
        redCheckerCount = 12;
    }

    public int getBlackCheckerCount() {
        return blackCheckerCount;
    }

    public int getRedCheckerCount() {
        return redCheckerCount;
    }

    public Coordinate getCoordinateFromPlayerInput(String playerInput) {
        return coordinates.get(playerInput);
    }

    public boolean isSelectionLegal(Coordinate coordinate, String color) {
        if (coordinate == null) {
            return false;
        }
        Piece piece = squares[coordinate.getXCoordinate()][coordinate.getYCoordinate()].getPiece();
        return (piece != null && color.equals(piece.getColor()));
    }

    public boolean isMoveLegal(Move move) {
        if (isSquareOccupied(move.getEndingXCoor(), move.getEndingYCoor())) {
            return false;
        }

        Square destinationSquare = squares[move.getEndingXCoor()][move.getEndingYCoor()];
        if (destinationSquare.getColor() == RED) {
            return false;
        }

        Piece piece = squares[move.getInitialXCoor()][move.getInitialYCoor()].getPiece();
        String color = piece.getColor();
        String rank = piece.getRank();
        if (rank.equals(Piece.PAWN_RANK)) {
            return isPawnMoveValid(color, move);
        } else {
            return isKingMoveValid(color, move);
        }

    }

    public void movePiece(Move move) {
        Square initialSquare = squares[move.getInitialXCoor()][move.getInitialYCoor()];
        Square destinationSquare = squares[move.getEndingXCoor()][move.getEndingYCoor()];

        destinationSquare.setPiece(initialSquare.getPiece());
        initialSquare.setPiece(null);

        Square captureSquare = getCaptureSquare(move);
        if (captureSquare != null) {
            if (captureSquare.getPiece().getColor().equals("Red")) {
                redCheckerCount--;
            } else {
                blackCheckerCount--;
            }
            captureSquare.setPiece(null);
        }
        if (destinationSquare.getPiece().getColor().equals("Red")) {
            if (didRedPawnReachKingZone(move)) {
                destinationSquare.getPiece().kingPiece();
            }
        }
        if (destinationSquare.getPiece().getColor().equals("Black")) {
            if (didBlackPawnReachKingZone(move)) {
                destinationSquare.getPiece().kingPiece();
            }
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("  1 2 3 4 5 6 7 8");
        builder.append("\n");
        for (int i = 0; i < 8; i++) {
            builder.append(letters[i]).append(" ");
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].getPiece() != null) {
                    builder.append(squares[i][j].getPiece().toString());
                } else {
                    builder.append(squares[i][j].getColor()).append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void placeTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = (i + j) % 2 == 0 ? new Square(BLACK) : new Square(RED);
            }
        }
    }

    private void placeBlackCheckers() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    squares[i][j].setPiece(new Piece("Black"));
                }
            }
        }
    }

    private void placeRedCheckers() {
        for (int i = 7; i > 4; i--) {
            for (int j = 7; j > -1; j--) {
                if ((i + j) % 2 == 0) {
                    squares[i][j].setPiece(new Piece("Red"));
                }
            }
        }
    }

    private void initializeCoordinates() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                coordinates.put(letters[i] + (j + 1), new Coordinate(i, j));
            }
        }
    }

    private boolean isSquareOccupied(int x, int y) {
        return squares[x][y].getPiece() != null;
    }

    private boolean isPawnMoveValid(String color, Move move) {
        if (!isPawnDirectionValid(color, move)) {
            return false;
        }
        if (didPawnMoveDiagonallyOneSpace(color, move)) {
            return true;
        }
        if (didPawnMoveDiagonallyTwoSpaces(color, move)) {
            Square captureSquare = getCaptureSquare(move);
            if (captureSquare.getPiece() == null) {
                return false;
            }
            return !captureSquare.getPiece().getColor().equals(color);
        }

        return false;
    }

    private boolean isPawnDirectionValid(String color, Move move) {
        if (color.equals("Red")) {
            return isRedPawnDirectionValid(move);
        }
        if (color.equals("Black")) {
            return isBlackPawnDirectionValid(move);
        }
        return false;
    }

    private boolean isRedPawnDirectionValid(Move move) {
        return isMovementUp(move);
    }

    private boolean isBlackPawnDirectionValid(Move move) {
        return isMovementDown(move);
    }

    private boolean isMovementUp(Move move) {
        return move.getEndingXCoor() < move.getInitialXCoor();
    }

    private boolean isMovementDown(Move move) {
        return move.getEndingXCoor() > move.getInitialXCoor();
    }

    private boolean didPawnMoveDiagonallyOneSpace(String color, Move move) {
        if (color.equals("Red")) {
            return didRedPawnMoveDiagonallyOneSpace(move);
        }

        if (color.equals("Black")) {
            return didBlackPawnMoveDiagonallyOneSpace(move);
        }
        return false;
    }

    private boolean didRedPawnMoveDiagonallyOneSpace(Move move) {
        int upLeftXCoord = move.getInitialXCoor() - 1;
        int upLeftYCoord = move.getInitialYCoor() - 1;
        int upRightXCoord = move.getInitialXCoor() - 1;
        int upRightYCoord = move.getInitialYCoor() + 1;


        if (move.getEndingXCoor() == upLeftXCoord && move.getEndingYCoor() == upLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == upRightXCoord && move.getEndingYCoor() == upRightYCoord);
        }
    }

    private boolean didBlackPawnMoveDiagonallyOneSpace(Move move) {
        int downLeftXCoord = move.getInitialXCoor() + 1;
        int downLeftYCoord = move.getInitialYCoor() - 1;
        int downRightXCoord = move.getInitialXCoor() + 1;
        int downRightYCoord = move.getInitialYCoor() + 1;

        if (move.getEndingXCoor() == downLeftXCoord && move.getEndingYCoor() == downLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == downRightXCoord && move.getEndingYCoor() == downRightYCoord);
        }
    }

    private boolean didPawnMoveDiagonallyTwoSpaces(String color, Move move) {
        if (color.equals("Red")) {
            return didRedPawnMoveDiagonallyTwoSpaces(move);
        }

        if (color.equals("Black")) {
            return didBlackPawnMoveDiagonallyTwoSpaces(move);
        }
        return false;
    }

    private boolean didRedPawnMoveDiagonallyTwoSpaces(Move move) {
        int upLeftXCoord = move.getInitialXCoor() - 2;
        int upLeftYCoord = move.getInitialYCoor() - 2;
        int upRightXCoord = move.getInitialXCoor() - 2;
        int upRightYCoord = move.getInitialYCoor() + 2;

        if (move.getEndingXCoor() == upLeftXCoord && move.getEndingYCoor() == upLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == upRightXCoord && move.getEndingYCoor() == upRightYCoord);
        }
    }

    private boolean didBlackPawnMoveDiagonallyTwoSpaces(Move move) {
        int downLeftXCoord = move.getInitialXCoor() + 2;
        int downLeftYCoord = move.getInitialYCoor() - 2;
        int downRightXCoord = move.getInitialXCoor() + 2;
        int downRightYCoord = move.getInitialYCoor() + 2;

        if (move.getEndingXCoor() == downLeftXCoord && move.getEndingYCoor() == downLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == downRightXCoord && move.getEndingYCoor() == downRightYCoord);
        }
    }

    private boolean isKingMoveValid(String color, Move move) {
        if (didKingMoveDiagonallyOneSpace(move)) {
            return true;
        }
        if (didKingMoveDiagonallyTwoSpaces(move)) {
            Square captureSquare = getCaptureSquare(move);
            assert captureSquare != null;
            if (captureSquare.getPiece() == null) {
                return false;
            }
            return !captureSquare.getPiece().getColor().equals(color);
        }

        return false;
    }

    private boolean didKingMoveDiagonallyOneSpace(Move move) {
        int upLeftXCoord = move.getInitialXCoor() - 1;
        int upLeftYCoord = move.getInitialYCoor() - 1;
        int upRightXCoord = move.getInitialXCoor() - 1;
        int upRightYCoord = move.getInitialYCoor() + 1;

        int downLeftXCoord = move.getInitialXCoor() + 1;
        int downLeftYCoord = move.getInitialYCoor() - 1;
        int downRightXCoord = move.getInitialXCoor() + 1;
        int downRightYCoord = move.getInitialYCoor() + 1;


        if (move.getEndingXCoor() == upLeftXCoord && move.getEndingYCoor() == upLeftYCoord) {
            return true;
        }

        if (move.getEndingXCoor() == upRightXCoord && move.getEndingYCoor() == upRightYCoord) {
            return true;
        }
        if (move.getEndingXCoor() == downLeftXCoord && move.getEndingYCoor() == downLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == downRightXCoord && move.getEndingYCoor() == downRightYCoord);
        }
    }

    private boolean didKingMoveDiagonallyTwoSpaces(Move move) {
        int upLeftXCoord = move.getInitialXCoor() - 2;
        int upLeftYCoord = move.getInitialYCoor() - 2;
        int upRightXCoord = move.getInitialXCoor() - 2;
        int upRightYCoord = move.getInitialYCoor() + 2;

        int downLeftXCoord = move.getInitialXCoor() + 2;
        int downLeftYCoord = move.getInitialYCoor() - 2;
        int downRightXCoord = move.getInitialXCoor() + 2;
        int downRightYCoord = move.getInitialYCoor() + 2;


        if (move.getEndingXCoor() == upLeftXCoord && move.getEndingYCoor() == upLeftYCoord) {
            return true;
        }

        if (move.getEndingXCoor() == upRightXCoord && move.getEndingYCoor() == upRightYCoord) {
            return true;
        }
        if (move.getEndingXCoor() == downLeftXCoord && move.getEndingYCoor() == downLeftYCoord) {
            return true;
        } else {
            return (move.getEndingXCoor() == downRightXCoord && move.getEndingYCoor() == downRightYCoord);
        }
    }

    private Square getCaptureSquare(Move move) {
        int upLeftXCoord = move.getInitialXCoor() - 2;
        int upLeftYCoord = move.getInitialYCoor() - 2;

        if (move.getEndingXCoor() == upLeftXCoord && move.getEndingYCoor() == upLeftYCoord) {
            return getSquareAtCoord(move.getInitialXCoor() - 1, move.getInitialYCoor() - 1);
        }

        int upRightXCoord = move.getInitialXCoor() - 2;
        int upRightYCoord = move.getInitialYCoor() + 2;

        if (move.getEndingXCoor() == upRightXCoord && move.getEndingYCoor() == upRightYCoord) {
            return getSquareAtCoord(move.getInitialXCoor() - 1, move.getInitialYCoor() + 1);
        }

        int downLeftXCoord = move.getInitialXCoor() + 2;
        int downLeftYCoord = move.getInitialYCoor() - 2;

        if (move.getEndingXCoor() == downLeftXCoord && move.getEndingYCoor() == downLeftYCoord) {
            return getSquareAtCoord(move.getInitialXCoor() + 1, move.getInitialYCoor() - 1);
        }

        int downRightXCoord = move.getInitialXCoor() + 2;
        int downRightYCoord = move.getInitialYCoor() + 2;

        if (move.getEndingXCoor() == downRightXCoord && move.getEndingYCoor() == downRightYCoord) {
            return getSquareAtCoord(move.getInitialXCoor() + 1, move.getInitialYCoor() + 1);
        }
        return null;
    }

    private Square getSquareAtCoord(int x, int y) {
        return squares[x][y];
    }

    private boolean didRedPawnReachKingZone(Move move) {
        return move.getEndingXCoor() == 0;
    }

    private boolean didBlackPawnReachKingZone(Move move) {
        return move.getEndingXCoor() == 7;
    }

}