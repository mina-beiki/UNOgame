package com.company;

import java.util.Objects;

/**
 * Card with color and number , a subclass for Colored class type card . Doesn't have special act
 * and doesn't make change in turns or direction .
 */
public class Numeric extends Colored {
    private int number;

    /**
     * Makes a new numeric card with given data .
     *
     * @param color  color String to be set
     * @param number Integer number Value
     */
    public Numeric(String color, int number) {
        super(color);
        this.number = number;
    }

    /**
     * Doesn't have any special act .
     *
     * @param currentTable current table to be changed
     */
    @Override
    public void act(Table currentTable) {
        //nothing to write
    }

    /**
     * checks if two numeric cards are the same or not .
     *
     * @param o object to be checked
     * @return boolean true if they are the same and false if they are not .
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Numeric)) return false;
        if (!super.equals(o)) return false;
        Numeric numeric = (Numeric) o;
        return number == numeric.number;
    }

    /**
     * Gets card hashcode
     *
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }

    /**
     * Gets number of the card
     *
     * @return int number value
     */
    public int getNumber() {
        return number;
    }
}
