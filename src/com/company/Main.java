package com.company;

import java.util.Scanner;

/**
 * Prints the menu and interacts with user . Get number of players and starts the game .
 *
 * @author Mina Beiki
 * @version 21.04.2020
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to UNO !");
        System.out.println("Select your mode : (Just enter index number)");
        System.out.println("1. Player VS Computer players ");
        System.out.println("2. Multi player ");
        int mode = scanner.nextInt();
        if(mode==1) {
            System.out.println("Number of players : (Just type number ) ");
            System.out.println("3 Players");
            System.out.println("4 Players");
            System.out.println("5 Players");
            int numOfPlayers = scanner.nextInt();
            if (numOfPlayers < 3 || numOfPlayers > 5)
                System.out.println("Wrong input !");
            Table myGame = new Table(numOfPlayers,1);
            myGame.dealHands();
            myGame.playGame(1);
            myGame.printScoreBoard();
        }
        if(mode==2){
            System.out.println("Number of players : ");
            System.out.println("3 Players");
            System.out.println("4 Players");
            System.out.println("5 Players");
            int numOfPlayers = scanner.nextInt();
            if (numOfPlayers < 3 || numOfPlayers > 5)
                System.out.println("Wrong input !");
            Table myGame = new Table(numOfPlayers,2);
            myGame.dealHands();
            myGame.playGame(2);
            myGame.printScoreBoard();
        }

    }
}
