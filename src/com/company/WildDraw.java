package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Card type which gives player the ability of both choosing the color and adding
 * 4 cards to te hand of next player .It is a subclass of Wild class .  The next player
 * which was fined , loses his turn and the next player after him continues the game .
 * It should be noticed that the amount of penalties is not always 2 , based on how  many draw2 cards are
 * put on each other in a row , it can get higher with ratio = 4 .
 */
public class WildDraw extends Wild {

    private static final String ANSI_BlACK_BACKGROUND = "\u001B[47m";

    /**
     * Gets color for wild cards when printing .
     * @return String color
     */
    @Override
    public String getBackground(){
        return ANSI_BlACK_BACKGROUND;
    }

    /**
     * If it is a computer player chooses a random color , and if it is user , gets the color
     * and adds the penalty cards to the next player . It also calculates the amount of penalty
     * cards for next player .
     * @param currentTable Table class to be changed
     */
    @Override
    public void act(Table currentTable) {
        int trn = currentTable.getTurn();
        Player player = currentTable.getPlayers().get(trn-1);

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
        ArrayList<Card> cards = new ArrayList<>();
        int num = 0 ;
        //starts from the last card , and check if it is wild draw or not :
        if(currentTable.getPlayedCards().size()>2) {
            int i = currentTable.getPlayedCards().size() - 1;
            while (currentTable.getPlayedCards().get(i) instanceof Draw2) {
                num++;
                i--;
            }
        }
        int penalty = num * 4 ;
        cards = currentTable.getDeck().getCards(penalty);

        int turn = currentTable.getTurn();
        int turn2 = turn + 1; //player to add cards and lose his turn
        int turn3 = turn + 2; //new turn to br set

        if(turn+1 == currentTable.getPlayers().size()){
            turn2 = 1 ;
        }
        currentTable.getPlayers().get(turn2).addCards(cards);
        if(turn == currentTable.getPlayers().size()){
            turn3 = 2 ;
        }
        if(turn+1 == currentTable.getPlayers().size()){
            turn3 = 1 ;
        }
        currentTable.setTurn(turn3);
    }

}
