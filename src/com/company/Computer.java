package com.company;

/**
 * Represents a computer player in game . It is a subclass for Player class , Which can
 * get the input card in every round .
 */
public class Computer extends Player {
    /**
     * In every round finds a card from the hand of cards , and check the type
     * with the center card and if it matches the type , returns the card .
     * For wildDraw cards put them only when in need . And if it has nothing
     * to put returns null .
     * If player can not put anything , take a card from deck .
     * @param centerCard center card of the table
     * @param deck deck of cards we have right now
     * @return Card new card to be put
     */
    public Card getInputCard(Card centerCard , Deck deck){
        Card cardToPut = null ;
        for(Card card : cards){
            if(deck.checkCardsType(centerCard,card)) {
                cardToPut = card;
                if(!(cardToPut instanceof WildDraw) ){
                    return cardToPut;
                }
                break;
            }
        }
        //if comp has chosen a wild draw card :
        if(cardToPut instanceof WildDraw){
            //check if there are any other possibilities :
            if(checkOtherCards(centerCard)){
                Card card ;
                card = getOtherCard(centerCard,deck);
                //if yes , put another card :
                return card;
            }
            else {
                //put the wild draw card :
                return cardToPut;
            }
        }

        else {
            //take a card from deck :
            cardToPut = takeCard(deck);
            if(deck.checkCardsType(centerCard,cardToPut)) {
                return cardToPut;
            }
            else
                cards.add(cardToPut);
        }

        //nothing to put :
        return null ;
    }


}
