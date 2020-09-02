package com.company;

import java.util.ArrayList;

/**
 * Represents a player in game which is a subclass for two types of players , Computer players and
 * user players (Which uses the console ) . Each player has a set of cards which at the start of the
 * game is given to all of them and is 7 for each . ( All random )
 * Score for each player is the sum of the score for each card .
 */
public class Player {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";

    private int score;
    protected ArrayList<Card> cards;

    /**
     * Makes a new player . At first all players turns are assumed false ,
     * because they haven't started playing .
     */
    public Player() {
        // if turn is false , player is not playing , and if turn is true , player is playing .
        cards = new ArrayList<>();
        score = 0;
    }

    /**
     * Gets the cards of a player
     *
     * @return  Array list of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Sets cards of a player
     *
     * @param cards Array list of cards
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Get number of the cards a player has
     *
     * @return int Number
     */
    public int getNumberOfCards() {
        return cards.size();
    }


    /**
     * Puts a card on the table
     *
     * @param cardToPut Card card to be put
     */
    public void putCard(Card cardToPut) {
        cards.remove(cardToPut);
    }


    /**
     * Add new cards to the hand of cards the player have
     *
     * @param cardsToAdd Array list of cards to be added
     */
    public void addCards(ArrayList<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }

    /**
     * Checks if there are any other possibilities to put another card . (It
     * is used to prevent putting wild card but only when in need . )
     *
     * @param centerCard Card center card on the table
     * @return true , if there are other options and false , if there are not .
     */
    public boolean checkOtherCards(Card centerCard) {
        for (Card card : this.getCards()) {
            if (card instanceof Colored || card instanceof ColorChanger) {
                return true;
            }
        }
        return false;
    }

    /**
     * If there is any other possibilities to put card , gets that card .
     *
     * @param centerCard Card center card on the table
     * @param deck       Deck deck of cards
     * @return Card card to be used
     */
    public Card getOtherCard(Card centerCard, Deck deck) {
        for (Card card : this.getCards()) {
            if (card instanceof Colored || card instanceof ColorChanger) {
                if (deck.checkCardsType(centerCard, card))
                    return card;
            }
        }
        return null;
    }

    /**
     * Takes a card from deck .
     *
     * @param deck Deck deck of cards
     * @return Card new card token from deck
     */
    public Card takeCard(Deck deck) {
        ArrayList<Card> cards = new ArrayList<>();
        cards = deck.getCards(1);
        return cards.get(0);
    }

    /**
     * Gets the score of player based on the score of each card .
     *
     * @return int score of player
     */
    public int getScore() {
        for (Card card : cards) {
            if (card instanceof Numeric) {
                score += ((Numeric) card).getNumber();
            }
            if (card instanceof Skip || card instanceof Reverse || card instanceof Draw2) {
                score += 20;
            }
            if (card instanceof WildDraw || card instanceof ColorChanger) {
                score += 50;
            }
        }
        return score;
    }

    /**
     * Print all cards of a player . ( Based on lines )
     */
    public void printCards() {
        //each for,  prints a line
        //line 1 :
        for (Card card : cards) {
            if (card instanceof Colored) {
                //prints line 1 of cards :
                Colored coloredCard = (Colored) card;
                System.out.print("  " + coloredCard.getColor() + "    _____    " + ANSI_RESET);
            }
            if (card instanceof Wild) {
                //prints line 1 of cards :
                Wild wildCard = (Wild) card;
                System.out.print("  " + wildCard.getBackground() + "    _____    " + ANSI_RESET);
            }
        }
        System.out.println();
        // line 2 :
        for (Card card : cards) {
            if (card instanceof Colored) {
                Colored coloredCard = (Colored) card;
                System.out.print("  " + coloredCard.getColor() + "   |     |   " + ANSI_RESET);
            }
            if (card instanceof Wild) {
                Wild wildCard = (Wild) card;
                System.out.print("  " + wildCard.getBackground() + "   |     |   " + ANSI_RESET);
            }
        }
        System.out.println();
        //line 3 :
        for (Card card : cards) {
            if (card instanceof Colored) {
                Colored coloredCard = (Colored) card;
                if (card instanceof Numeric) {
                    System.out.print("  " + coloredCard.getColor() + ANSI_BLACK + "   |  " + ((Numeric) card).getNumber() + "  |   " + ANSI_RESET);
                }
                if (card instanceof Skip) {
                    String str = "\uD83D\uDEAB";
                    System.out.print("  " + coloredCard.getColor() + ANSI_BLACK + "   |Skip" + str + "|   " + ANSI_RESET);
                }
                if (card instanceof Reverse) {
                    System.out.print("  " + coloredCard.getColor() + ANSI_BLACK + "   | Rev |   " + ANSI_RESET);
                }
                if (card instanceof Draw2) {
                    System.out.print("  " + coloredCard.getColor() + ANSI_BLACK + "   | +2+ |   " + ANSI_RESET);
                }
            }
            if (card instanceof WildDraw) {
                WildDraw wildDraw = (WildDraw) card;
                System.out.print("  " + wildDraw.getBackground() + ANSI_BLACK + "   | +4+ |   " + ANSI_RESET);
            }
            if (card instanceof ColorChanger) {
                ColorChanger colorChanger = (ColorChanger) card;
                System.out.print("  " + colorChanger.getBackground() + ANSI_BLACK + "   |Color|   " + ANSI_RESET);
            }
        }
        System.out.println();
        //line4:
        for (Card card : cards) {
            if (card instanceof Colored) {
                Colored coloredCard = (Colored) card;
                System.out.print("  " + coloredCard.getColor() + "   |_____|   " + ANSI_RESET);
            }
            if (card instanceof Wild) {
                Wild wildCard = (Wild) card;
                System.out.print("  " + wildCard.getBackground() + "   |_____|   " + ANSI_RESET);
            }
        }
        System.out.println();
        //line5:
        for (Card card : cards) {
            if (card instanceof Colored) {
                Colored coloredCard = (Colored) card;
                System.out.print("  " + coloredCard.getColor() + "             " + ANSI_RESET);
            }
            if (card instanceof Wild) {
                Wild wildCard = (Wild) card;
                System.out.print("  " + wildCard.getBackground() + "             " + ANSI_RESET);
            }
        }
        System.out.println();
    }
}
