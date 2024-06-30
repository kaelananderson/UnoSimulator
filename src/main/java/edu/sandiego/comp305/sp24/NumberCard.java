package edu.sandiego.comp305.sp24;

/**
 * NumberCard which is a basic type of card that
 * just has the color and number
 */
class NumberCard extends Card {
    protected int number;

    /**
     * constructor for a NumberCard with a specified color and number
     *
     * @param color  color of card
     * @param number  number on card
     */
    public NumberCard(String color, int number) {
        // call superclass to set the color
        super(color);
        this.number = number;
    }

    /**
     * gets number of card
     *
     * @return number on card
     */
    public int getNumber() {
        return number;
    }

    /**
     * plays card by checking if the color or number matches the top card on the play deck
     *
     * @param topCard card that is currently on top of the play deck
     * @return true if the play is valid
     */
    @Override
    public boolean playCard(Card topCard) {
        if (this.color.equals(topCard.getColor()) || this.number == ((NumberCard) topCard).getNumber()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return color + " " + number;
    }
}
