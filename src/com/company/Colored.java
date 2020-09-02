package com.company;

import java.util.Objects;

/**
 * This class is for all Colored type cards , which is a superclass for Skip , Reserve , Draw2 and Numeric classes .
 * All cards which are colored , consists of a color , which can be set or get .
 */
public abstract class Colored extends Card {
    private String color;

    /**
     * Makes a new colored card with given color .
     *
     * @param color new card color
     */
    public Colored(String color) {
        this.color = color;
    }

    /**
     * Gets the color of card
     *
     * @return String Color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of card
     *
     * @param color Color string
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Checks if two Colored cards are the same or not by checking their fields
     * and their type .
     *
     * @param o object to be checked
     * @return boolean true , if they are the same and false if they are not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colored)) return false;
        if (!super.equals(o)) return false;
        Colored colored = (Colored) o;
        return getColor().equals(colored.getColor());
    }

    /**
     * Gets card hashcode
     *
     * @return int Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }

}
