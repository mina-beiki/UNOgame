package com.company;

import java.util.Scanner;

/**
 * Represents a player which uses console for playing in game . It is a subclass
 * of Player class .
 */
public class User extends Player {
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * In every round gets a card index from the user , and check the type
     * with the center card and if it matches the type , returns the card .
     * For wildDraw cards put them only when in need . And if it has nothing
     * to put returns null .
     * If user can not put anything , take a card from deck .
     * @param centerCard center card of the table
     * @param deck deck of cards we have right now
     * @return Card new card to be put
     */
    public Card getInputCard(Card centerCard , Deck deck ){
        Scanner scanner = new Scanner(System.in);
        int numberOfCards = cards.size();

        System.out.println("Card to put : (Enter the index number)");
        System.out.println(" 0 = take a card from deck ");
        int index = scanner.nextInt();
        if(index==0) {
            Card card;
            card = takeCard(deck);
            cards.add(card);
            printCards();
            System.out.println();
            System.out.println("You have token a new card from deck . ");
            if (deck.checkCardsType(centerCard, cards.get(index))) {
                System.out.println();
                System.out.println("You can put it ! (Enter card index)");
                index = scanner.nextInt();
                return card;
            }else{
                System.out.println("Oops ! You cannot put it .");
            }
        }else {
            index -= 1;

            if (index > numberOfCards || index < 0)
                System.out.println(" Input is not correct .  ");

                // if the type doesn't match :
            else if (!(deck.checkCardsType(centerCard, cards.get(index)))) {
                System.out.println(cards.get(index).getClass().getName());
                System.out.println(centerCard.getClass().getName());
                System.out.println("Wrong type ! ");
                return null;
            }

            if (deck.checkCardsType(centerCard, cards.get(index))) {
                return cards.get(index);
            }

            //if the player has chosen wild draw card :
            if (cards.get(index) instanceof WildDraw) {
                if (checkOtherCards(centerCard)) {
                    //choose another card :
                    System.out.println();
                    System.out.println("Be careful ! Choose WildDraw cards , Only when in need !");
                    System.out.println("Card to put : (Enter the index number) ");
                    //gets another card :
                    return getOtherCard(centerCard, deck);
                } else {
                    //put the wildDraw card :
                    return cards.get(index);
                }
            }
        }

        return null ;
    }


}
