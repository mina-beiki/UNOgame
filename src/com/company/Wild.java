package com.company;

/**
 * One group of cards' types and is a subclass for Card class and Superclass for WildDraw and
 * ColorChanger classes . All Wild cards have the ability to choose the color for
 * next players but wild draw cards do even more !
 */
public abstract class Wild extends Card {

    protected static final String ANSI_BlACK_BACKGROUND = "\u001B[47m";
    protected static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    protected static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    protected static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    protected static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_BLACK = "\u001B[30m";
    //The color chosen by the player to be put next :
    protected String playColor;

    public abstract String getBackground();

    /**
     * When playing , User and Computer both need to choose a color between all colors so
     * this color table is printed to get their decision .
     */
    public void printColors() {
        System.out.println(ANSI_RED_BACKGROUND + "       " + ANSI_GREEN_BACKGROUND + "       " + ANSI_RESET);
        System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + "   1   " + ANSI_GREEN_BACKGROUND + "   2   " + ANSI_RESET);
        System.out.println(ANSI_RED_BACKGROUND + "       " + ANSI_GREEN_BACKGROUND + "       " + ANSI_RESET);
        System.out.println(ANSI_BLUE_BACKGROUND + "       " + ANSI_YELLOW_BACKGROUND + "       " + ANSI_RESET);
        System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + "   3   " + ANSI_YELLOW_BACKGROUND + "   4   " + ANSI_RESET);
        System.out.println(ANSI_BLUE_BACKGROUND + "       " + ANSI_YELLOW_BACKGROUND + "       " + ANSI_RESET);
    }

    /**
     * Gets the color set for game
     *
     * @return String color
     */
    public String getPlayColor() {
        return playColor;
    }
}
