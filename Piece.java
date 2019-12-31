package main.java;

public class Piece {
	private static String PAWN_RANK = "Pawn";
	private static String KING_RANK = "King";
	
    private String color;

    private String rank;
    
    Piece(){
    	rank = PAWN_RANK;

    }
    
    public String getColor() {return color;}
    
    public String getRank() {return rank;}

    public void kingPiece() {


    }

}
