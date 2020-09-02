package com.company;

import java.util.ArrayList;

/**
 * This is a card type which inherits from Colored class. When this card is put , next player
 * should take 2 cards from deck and loses his turn ; so the next player after him continues the game .
 * It should be noticed that the amount of penalties is not always 2 , based on how  many draw2 cards are
 * put on each other in a row , it can get higher with ratio = 2 .
 */
public class Draw2 extends Colored {

    /**
     * Makes a new Draw2 Card .
     *
     * @param color color to be set for card
     */
    public Draw2(String color) {
        super(color);
    }

    /**
     * Adds 2 card from deck to the next player and the next player also loses his turn .It should be noticed
     * that the amount of penalties is not always 2 , based on how  many draw2 cards are
     * put on each other in a row , it can get higher with ratio = 2 .
     *
     * @param currentTable Table current table to be changed
     */
    @Override
    public void act(Table currentTable) {
        int num = 0;
        ArrayList<Card> cards = new ArrayList<>();

        //Find amount of penalties:
        if(currentTable.getPlayedCards().size()>2) {
            int i = currentTable.getPlayedCards().size() - 1;
            while (currentTable.getPlayedCards().get(i) instanceof Draw2) {
                num++;
                i--;
            }
        }
        int penalty = num * 2;
        cards = currentTable.getDeck().getCards(penalty);

        int turn = currentTable.getTurn();
        int turn2 = turn + 1 ; //player to add cards and lose his turn
        int turn3 = turn + 2; //new turn to be set

        if (turn + 1 == currentTable.getPlayers().size()) {
            turn2 = 1;
        }
        currentTable.getPlayers().get(turn2).addCards(cards);
        if (turn == currentTable.getPlayers().size()) {
            turn3 = 2;
        }
        if (turn + 1 == currentTable.getPlayers().size()) {
            turn3 = 1;
        }
        currentTable.setTurn(turn3);
    }


}
