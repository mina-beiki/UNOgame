package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * This class is for Color changer type card ; Which when it is put , user can choose a new color
 * and rest of the players should put cards based on that color .
 */
public class ColorChanger extends Wild {

    private static final String ANSI_BlACK_BACKGROUND = "\u001B[47m";

    /**
     * Gets the black color for printing wild cards
     *
     * @return String color ANSI code
     */
    @Override
    public String getBackground() {
        return ANSI_BlACK_BACKGROUND;
    }

    /**
     * Gets a color from player , for computer players a random color is chosen and
     * sets the play color parameter which defines the color which has been set for playing .
     * Doesn't change anything in turns or directions .
     *
     * @param currentTable Table current table to be changed
     */
    @Override
    public void act(Table currentTable) {
        int turn = currentTable.getTurn();
        Player player = currentTable.getPlayers().get(turn-1);

        if (player instanceof Computer) {
            Computer computer = (Computer) player;
            printColors();
            int index;
            String str = "";
            Random random = new Random();
            index = random.nextInt(4) + 1;
            //red :
            if (index == 1) {
                playColor = "\u001B[41m";
                str = "red";
            }
            //green :
            if (index == 2) {
                playColor = "\u001B[42m";
                str = "green";
            }
            //blue :
            if (index == 3) {
                playColor = "\u001B[44m";
                str = "blue";
            }
            //yellow :
            if (index == 4) {
                playColor = "\u001B[43m";
                str = "yellow";
            }
            System.out.println("Computer chose " + str + " .");
        } else if (player instanceof User) {
            int index;
            User user = (User) player;
            printColors();
            System.out.println();
            System.out.println("Which color?");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt();
            //red :
            if (index == 1)
                playColor = "\u001B[41m";
            //green :
            if (index == 2)
                playColor = "\u001B[42m";
            //blue :
            if (index == 3)
                playColor = "\u001B[44m";
            //yellow :
            if (index == 4)
                playColor = "\u001B[43m";

        }
        //doesn't change anything in turns , or other player cards
    }
}




