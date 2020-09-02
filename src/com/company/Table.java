package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Controls over the process of the game . Contains the players , deck , center card ,
 * turns and direction . It can deal hands to players , check if the game has finished or not ,
 * print the score board and play the game .
 *
 * @author Mina Beiki
 * @version 21.04.2020
 */
public class Table {

    Card centerCard;
    ArrayList<Player> players;
    Deck deck;
    //symbol for understanding the direction , 1 : clockwise , and 2 : anticlockwise
    int direction;
    //list of cards which has been played till now : ( for finding the penalties )
    ArrayList<Card> playedCards;
    int turn;

    /**
     * Makes a new table for games . A table is consisted of Array list of cards , Array list of players ,
     * Array list of the cards that have been played , direction and it has access to the deck of cards .
     *
     * @param numberOfPlayers int number of players
     * @param mode int mode of the game , which can be multi player or with computer players
     */
    public Table(int numberOfPlayers, int mode) {
        playedCards = new ArrayList<>();
        players = new ArrayList<>();
        playedCards = new ArrayList<>();
        deck = new Deck();
        direction = 1;

        if (mode == 1) {
            //make computer players :
            for (int i = 0; i < numberOfPlayers - 1; i++) {
                Player comp = new Computer();
                players.add(comp);
            }
            //make user :
            Player user = new User();
            players.add(user);
        }
        if (mode == 2) {
            for (int i = 0; i < numberOfPlayers; i++) {
                Player user = new User();
                players.add(user);
            }
        }
    }

    /**
     * Before starting the game , give each player a hand of cards which is consisted
     * of 7 accidental cards from deck .
     */
    public void dealHands() {
        for (Player player : players) {
            ArrayList<Card> hand = new ArrayList<>();
            hand = deck.getHandOfCards();
            player.setCards(hand);
        }
    }

    /**
     * Starts with random player every time (starter player) and set the center card from the deck
     * which should be colored . In every round of game , checks if the game has finished or not
     * and if yes , prints the score board nd if not , plays the round .
     * In every round the player which it is his turn , puts a card and the card acts . Every last center
     * card will be put into deck .
     *
     * @param mode int mode for game which can be multi player or with computer players .
     */
    public void playGame(int mode) {
        int numOfPlayers = players.size();
        ArrayList<Player> temp = new ArrayList<>();
        //finds a random player to start the game :
        Random random = new Random();
        int index = random.nextInt(players.size());
        //set the center card :
        centerCard = deck.getColoredCard();
        playedCards.add(centerCard);

        temp.add(players.get(index));
        players.remove(players.get(index));
        temp.addAll(players);
        players.clear();
        players.addAll(temp);
        centerCard.act(this);

        if (mode == 1) {
            while (checkEndGame()) {
                for (turn = 1; turn <= numOfPlayers; turn++) {
                    //check if the first player has started or not :
                    printTableOfCards();
                    //put the card:
                    if (players.get(turn - 1) instanceof User) {
                        User user = (User) players.get(turn - 1);
                        Card cardToPut = user.getInputCard(centerCard, deck);
                        //if it doesn't have anything to put , even after taking a card from deck:
                        if (cardToPut == null) {
                            continue;
                        }
                        user.putCard(cardToPut);
                        deck.addCard(centerCard);
                        centerCard = cardToPut;
                        playedCards.add(centerCard);
                        centerCard.act(this);

                    }
                    if (players.get(turn - 1) instanceof Computer) {
                        Computer computer = (Computer) players.get(turn - 1);
                        Card cardToPut = computer.getInputCard(centerCard, deck);
                        //if it doesn't have anything to put , even after taking a card from deck:
                        if (cardToPut == null) {
                            continue;
                        }
                        computer.putCard(cardToPut);
                        deck.addCard(centerCard);
                        centerCard = cardToPut;
                        playedCards.add(centerCard);
                        centerCard.act(this);

                    }
                }
            }
        }
        if (mode == 2) {
            while (checkEndGame()) {
                for (turn = 1; turn <= numOfPlayers; turn++) {
                    //check if the first player has started or not :
                    printData();
                    players.get(turn-1).printCards();
                    //put the card:
                    User user = (User) players.get(turn - 1);
                    Card cardToPut = user.getInputCard(centerCard, deck);
                    //if it doesn't have anything to put , even after taking a card from deck:
                    if (cardToPut == null) {
                        continue;
                    }
                    user.putCard(cardToPut);
                    deck.addCard(centerCard);
                    centerCard = cardToPut;
                    playedCards.add(centerCard);
                    centerCard.act(this);
                }
            }
        }
    }

    /**
     * Prints the data for console .
     */
    public void printData() {
        String str = null;
        if (direction == 1)
            str = "Clockwise";
        else if (direction == 2)
            str = "AntiClockwise";
        System.out.println(" Direction : " + str);
        System.out.println();
        centerCard.print();
        System.out.println();
    }

    /**
     * Every round , prints the direction , User player cards , and all computer players number of cards
     * and also the center card .
     */
    public void printTableOfCards() {
        String str = null;
        if (direction == 1)
            str = "Clockwise";
        else if (direction == 2)
            str = "AntiClockwise";
        System.out.println(" Direction : " + str);
        System.out.println();
        centerCard.print();
        System.out.println();
        int ctr = 0;
        for (Player player : players) {
            if (player instanceof Computer) {
                Computer comp = (Computer) player;
                ctr++;
                System.out.println("Computer player number " + ctr + " : " + comp.getNumberOfCards());
            }
        }
        System.out.println();
        for (Player player : players) {
            if (player instanceof User) {
                System.out.println("Your cards : ");
                player.printCards();
            }
        }
        System.out.println();
    }


    /**
     * Prints scores from least to most for each player . player with least player is the
     * winner .
     */
    public void printScoreBoard() {
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<Player> temp = new ArrayList<>(); // players based scores from least to most
        //saves all scores in an array list :
        for (Player player : players) {
            scores.add(player.getScore());
        }
        //sort it from least to most :
        Collections.sort(scores);
        //save players in an array list based on their scores sorted :
        for (Integer score : scores) {
            for (Player player : players) {
                if (player.getScore() == score)
                    temp.add(player);
            }
        }
        //print scores :
        int ctr = 0;
        System.out.println("Score Table : ");
        for (Player player : temp) {
            ctr++;
            if (player instanceof Computer) {
                System.out.println("Computer player " + ctr + " : " + player.getScore());
            }
            if (player instanceof User) {
                System.out.println("You : " + player.getScore());
            }
        }

    }

    /**
     * Checks if the game has finished by checking if there is a player with no cards .
     *
     * @return true , if the game has finished and false , if it hasn't
     */
    public boolean checkEndGame() {
        int ctr = 0;
        for (Player player : players) {
            ctr++;
            if (player.getCards().size() == 0) {
                System.out.println("GAME IS FINISHED !");
                System.out.println("Winner : Player " + ctr);
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the players .
     *
     * @return Array list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the turn
     *
     * @param turn number to be set
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Gets the turn
     *
     * @return Integer number of turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Gets the cards which have been played till now
     *
     * @return Array list of Card
     */
    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }

    /**
     * Gets the deck
     *
     * @return Deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Sets the direction
     * @param direction int direction number
     */
    public void setDirection(int direction){
        this.direction = direction;
    }

    /**
     * Gets the direction
     * @return int direction number
     */
    public int getDirection(){
        return direction ;
    }


}
