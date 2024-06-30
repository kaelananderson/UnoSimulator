package edu.sandiego.comp305.sp24;

/**
 * class represents a basic card
 * provides methods to get the color of the card and to play the card
 */
class Card {
    protected String color;

    /**
     * constructor to create a card with a specific color
     *
     * @param color color of the card.
     */
    public Card(String color) {
        this.color = color;
    }


    /**
     * gets the color of this card
     *
     * @return color of the card
     */
    public String getColor() {
        return color;
    }


    /**
     * plays the card
     * placeholder for actual logic that needs to be overridden in subclass
     *
     * @return true if the play is successful, otherwise false
     */
    public boolean playCard(Card topCard) {
        return true;
    }




}
