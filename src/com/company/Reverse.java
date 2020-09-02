package com.company;

import java.util.Collections;

/**
 * Card type which changes the direction from where the player is . It is
 * a sub class for Colored class .
 */
public class Reverse extends Colored {
    /**
     * Makes a new Reverse card .
     *
     * @param color color to be set
     */
    public Reverse(String color) {
        super(color);
    }

    /**
     * For changing the direction , the array list of players in current table needs to
     * be reversed but we shouldn't change the turns .
     *
     * @param currentTable Table class to be changed
     */
    @Override
    public void act(Table currentTable) {
        if(currentTable.getDirection()==1){
            currentTable.setTurn(2);
        }
        if(currentTable.getDirection()==2){
            currentTable.setTurn(1);
        }
        Collections.reverse(currentTable.getPlayers());
    }
}
