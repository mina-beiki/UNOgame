package com.company;


import java.util.ArrayList;
import java.util.Random;

/**
 * Deck of cards at first consists of all the cards in game , and through the process of the game
 * players take cards from it and also the played cards which are put in the center are moved into
 * it afterwards ; so overall it has control over all cards and has an Array list of all cards .
 */
public class Deck {
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    ArrayList<Card> allCards;

    /**
     * Makes a new deck with 108 cards . Consisting of 100 colored cards and 8 wild cards .
     * 100 colored cards are 4 sets with different colors (red , yellow , green , blue )  , each 25 cards and consisting
     * of its different types and 8 wild cards includes 4 wildDraws and 4 colorChangers .
     */
    public Deck() {
        allCards = new ArrayList<>();
        /*First time making the cards : 4 sets of colored cards , each 25 cards consisting of 19 numeric , 2 skips ,
        2 reverses , 2 draw2 s . 8 wild cards consisting of 4 wild draws and 4 color changers .
         */
        //Colored cards : (number : 100 )
        for(int i=0 ; i<4 ; i++) {
            String color = null;
            if(i==0)
                color = ANSI_RED_BACKGROUND;
            if(i==1)
                color = ANSI_YELLOW_BACKGROUND;
            if(i==2)
                color = ANSI_GREEN_BACKGROUND;
            if(i==3)
                color = ANSI_BLUE_BACKGROUND;
            //numeric cards :
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 10; k++) {
                    Card coloredCard = new Numeric(color, k);
                    allCards.add(coloredCard);
                }
            }
            Card coloredCard = new Numeric(color, 0);
            allCards.add(coloredCard);
            //2 skips :
            for (int j = 0; j < 2; j++) {
                Card skipCard = new Skip(color);
                allCards.add(coloredCard);
            }
            //2 reverses :
            for (int j = 0; j < 2; j++) {
                Card reverseCard = new Reverse(color);
                allCards.add(reverseCard);
            }
            //2 draw2 s :
            for (int j = 0; j < 2; j++) {
                Card draw2Card = new Draw2(color);
                allCards.add(draw2Card);
            }
        }
        //wild cards : (number : 8 cards )
        for(int i=0 ; i<4 ; i++){
            Card ColorChanger = new ColorChanger();
            allCards.add(ColorChanger);
        }
        for(int i=0 ; i<4 ; i++){
            Card wildDraw = new WildDraw();
            allCards.add(wildDraw);
        }
    }

    /**
     * Gives 7 random cards which is called as a hand of cards .
     * @return Array list of Card
     */
    public ArrayList<Card> getHandOfCards(){
        //hand of cards consists of 7 random cards
        ArrayList<Card> hand = new ArrayList<>();
        Random random = new Random();
        int index = -1;
        for(int i=0 ; i<7 ; i++){
            index = random.nextInt(allCards.size());
            hand.add(allCards.get(index));
            allCards.remove(allCards.get(index));

        }
        return hand ;
    }

    /**
     * Gives one random colored card . Search among available deck cards till find a colored card and
     * returns it .
     * @return Colored Card
     */
    public Card getColoredCard(){
        Random random = new Random();
        int index;
        while(true) {
            index = random.nextInt(allCards.size());
            if (allCards.get(index) instanceof Colored)
                return allCards.get(index);
        }
    }

    /**
     * Gets number of cards from deck which the number is given in input.
     * @param numberOfCards int number of cards we want to get
     * @return  Array list of cards token from deck
     */
    public ArrayList<Card> getCards(int numberOfCards ){
        ArrayList<Card> cards = new ArrayList<>();
        Random random = new Random();
        int index ;
        for(int i=0 ; i<numberOfCards ; i++){
            index = random.nextInt(allCards.size());
            cards.add(allCards.get(index));
            allCards.remove(allCards.get(index));
        }
        return cards ;
    }

    /**
     * Gets center card and the card which is going to be put and check if their types
     * match or not .
     * @param card1 Card center card
     * @param card2 Card card which is going to be put
     * @return boolean true , if they match and false , if they don't  .
     */
    public  boolean checkCardsType (Card card1 , Card card2 ){
        //card1 in center card , card2 is the card which we put
        //colored cards :
        if(card1 instanceof Colored && card2 instanceof Colored){
            Colored colored1 = (Colored) card1 ;
            Colored colored2 = (Colored) card2 ;
            //same color:
            if(colored1.getColor().equals(colored2.getColor()))
                return true ;
            if(colored1 instanceof Numeric && colored2 instanceof Numeric){
                Numeric num1 = (Numeric) card1 ;
                Numeric num2 = (Numeric) card2 ;
                //same number :
                if(num1.getNumber()==num2.getNumber())
                    return true ;
            }
            //same kind of colored cards :
            if(colored1 instanceof Skip && colored2 instanceof Skip){
                return true ;
            }
            if(colored1 instanceof Reverse && colored2 instanceof Reverse){
                return true ;
            }
            if(colored1 instanceof Draw2 && colored2 instanceof Draw2){
                return true ;
            }
        }
        //Wild cards :
        if(card1 instanceof Wild && card2 instanceof Wild){
            return true;
        }
        //After color changer:
        if(card1 instanceof ColorChanger ){
            if(card2 instanceof Colored){
                if(((ColorChanger) card1).getPlayColor().equals(((Colored) card2).getColor()))
                    return true ;
            }
        }
        if(card1 instanceof WildDraw){
            if(card2 instanceof Colored){
                if(((WildDraw) card1).getPlayColor().equals(((Colored) card2).getColor()))
                    return true ;
            }
        }
        if(card2 instanceof Wild )
            return true;

        return false ;
    }

    /**
     * Adds a new card to deck
     * @param cardToAdd Card card to be added
     */
    public void addCard (Card cardToAdd){
        allCards.add(cardToAdd);
    }



}
