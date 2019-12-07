package com.javaacademy.projects.Projects;


import com.javaacademy.projects.Projects.CheckerGame.Checker;

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


