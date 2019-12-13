import java.util.*;
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

class Checker {
    Scanner input = new Scanner(System.in);
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
        System.out.println("Hello Welcome Player Here is were you choose if you want Computer or Human opponet");
        System.out.println("Type Computer for Computer Player or Type Human for Human Player");
        String choose = input.nextLine();
        if (choose.equals("Computer")) {
            playertwo = new Computer("Black");
        } else System.out.println("Human");
        playertwo= new Human("Black");
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

