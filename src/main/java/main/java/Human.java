package main.java;

import java.util.Scanner;

public class Human extends Player {
    Scanner input = new Scanner(System.in);
    boolean reselectPiece = false;

    Human(String color) {
        super(color);
    }

    Move makeMove(Board board) {
        Coordinate selectionCoordinate;
        Coordinate destinationCoordinate;
        reselectPiece = false;

        while (true) {
            selectionCoordinate = getSelectionCoordinateFromPlayerInput(board);
            destinationCoordinate = getDestinationCoordinateFromPlayerInput(board);

            if (!reselectPiece) {
                Move move = createMove(selectionCoordinate, destinationCoordinate);
                if (board.isMoveLegal(move)) {
                    return move;
                } else {
                    System.out.println("Move not legal. Try Again");
                    System.out.println(board.toString());
                }

            } else {
                System.out.println(board.toString());
                reselectPiece = false;
            }
        }
    }

    private Coordinate getSelectionCoordinateFromPlayerInput(Board board) {
        Coordinate selectionCoordinate;
        while (true) {
            System.out.println("Enter a Coordinate to select a piece. (A1-H8)");
            selectionCoordinate = board.getCoordinateFromPlayerInput(input.nextLine().toUpperCase());
            if (board.isSelectionLegal(selectionCoordinate, this.getColor())) {
                break;
            }
            System.out.println("Illegal Selection, Try Again");
        }
        return selectionCoordinate;
    }

    private Coordinate getDestinationCoordinateFromPlayerInput(Board board) {
        Coordinate destinationCoordinate = null;
        while (true) {
            System.out.println("Enter a Coordinate to move the selected piece to (A1-H8) or type \"reselect\" to make another selection.");
            String playerInput = input.nextLine().toUpperCase();
            if (playerInput.equals("RESELECT")) {
                reselectPiece = true;
                break;
            }

            destinationCoordinate = board.getCoordinateFromPlayerInput(playerInput);
            if (destinationCoordinate != null) {
                break;
            } else {
                System.out.println("Destination not on board. Try Again");
                System.out.println(board.toString());
            }
        }
        return destinationCoordinate;
    }

    private Move createMove(Coordinate selectionCoordinate, Coordinate destinationCoordinate) {
        return new Move(selectionCoordinate.getXCoordinate(),
                selectionCoordinate.getYCoordinate(),
                destinationCoordinate.getXCoordinate(),
                destinationCoordinate.getYCoordinate());
    }
}