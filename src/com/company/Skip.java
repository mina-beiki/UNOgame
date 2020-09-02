package com.company;

/**
 * Represents Skip card type which inherits Colored card type .When it is put ,
 * next player loses his turn and the next player after him continues the game .
 */
public class Skip extends Colored {

    /**
     * Makes a new Skip card with color given
     *
     * @param color color String
     */
    public Skip(String color) {
        super(color);
    }

    /**
     * Changes current turn ( current turn is the player who put this skip card) .
     *
     * @param currentTable Table class to be changed
     */
    @Override
    public void act(Table currentTable) {
        currentTable.setTurn(currentTable.getTurn() + 2);
    }
}
