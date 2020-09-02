package com.company;

/**
 * Represents an individual card which can have a type . Two main group of card types are
 * Colored cards and Wild cards which inherit from card class , and each are divided into more types .
 * Skip , Reverse , Draw2 are all movement card types which inherit from colored class and numeric type also
 * inherits from Colored card type .
 * Wild card type is divided into 2 groups , wild draw cards and color changer cards which both inherits from
 * Wild class . Each Card can act and fo the task meant for it and it also can be printed .
 */
public abstract class Card {
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Makes a card do the task which is made for it and make changes
     * to the table class .
     *
     * @param currentTable Table current table to be changed
     */
    public abstract void act(Table currentTable);


    /**
     * Gets hashcode for each card .
     *
     * @return int hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Checks if two cards are the same or not .
     *
     * @param obj Object to be checked
     * @return boolean true if they are the same , and false if they are not .
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Prints one card depending on which type it is .
     */
    public void print() {
        if (this instanceof Colored) {
            if (this instanceof Numeric) {
                System.out.println(((Numeric) this).getColor() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((Numeric) this).getColor() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((Numeric) this).getColor() + ANSI_BLACK + "   |  " + ((Numeric) this).getNumber() + "  |   " + ANSI_RESET);
                System.out.println(((Numeric) this).getColor() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((Numeric) this).getColor() + ANSI_BLACK + "             " + ANSI_RESET);
            }
            if (this instanceof Skip) {
                String str = "\uD83D\uDEAB";
                System.out.println(((Skip) this).getColor() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((Skip) this).getColor() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((Skip) this).getColor() + ANSI_BLACK + "   |Skip" + str + "|   " + ANSI_RESET);
                System.out.println(((Skip) this).getColor() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((Skip) this).getColor() + ANSI_BLACK + "             " + ANSI_RESET);
            }
            if (this instanceof Reverse) {
                System.out.println(((Reverse) this).getColor() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((Reverse) this).getColor() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((Reverse) this).getColor() + ANSI_BLACK + "   | Rev |   " + ANSI_RESET);
                System.out.println(((Reverse) this).getColor() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((Reverse) this).getColor() + ANSI_BLACK + "             " + ANSI_RESET);
            }
            if (this instanceof Draw2) {
                System.out.println(((Draw2) this).getColor() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((Draw2) this).getColor() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((Draw2) this).getColor() + ANSI_BLACK + "   | +2+ |   " + ANSI_RESET);
                System.out.println(((Draw2) this).getColor() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((Draw2) this).getColor() + ANSI_BLACK + "             " + ANSI_RESET);
            }
        }
        if (this instanceof Wild) {
            if (this instanceof WildDraw) {
                System.out.println(((WildDraw) this).getBackground() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((WildDraw) this).getBackground() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((WildDraw) this).getBackground() + ANSI_BLACK + "   | +4+ |   " + ANSI_RESET);
                System.out.println(((WildDraw) this).getBackground() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((WildDraw) this).getBackground() + ANSI_BLACK + "             " + ANSI_RESET);
            }
            if (this instanceof ColorChanger) {
                System.out.println(((ColorChanger) this).getBackground() + ANSI_BLACK + "    _____    " + ANSI_RESET);
                System.out.println(((ColorChanger) this).getBackground() + ANSI_BLACK + "   |     |   " + ANSI_RESET);
                System.out.println(((ColorChanger) this).getBackground() + ANSI_BLACK + "   |Color|   " + ANSI_RESET);
                System.out.println(((ColorChanger) this).getBackground() + ANSI_BLACK + "   |_____|   " + ANSI_RESET);
                System.out.println(((ColorChanger) this).getBackground() + ANSI_BLACK + "             " + ANSI_RESET);
            }
        }
    }

}
