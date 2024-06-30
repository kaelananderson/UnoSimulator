package edu.sandiego.comp305.sp24;


/**
 * Represents a WildCard in the Uno game, which can be either a regular wild card or a "Draw Four" wild card.
 * This class interacts with the game manager to enforce game rules when the card is played.
 */
class WildCard extends Card {
    private boolean isDrawFour;
    private GameManagement gameManager;  // Reference to the game management system


    /**
     * Constructs a WildCard with specified color, whether it is a "Draw Four" card, and the game manager.
     *
     * @param isDrawFour true if the card is a "Draw Four" wild card, false otherwise.
     * @param gameManager The game management system that handles game logic and state.
     */
    public WildCard(boolean isDrawFour, GameManagement gameManager) {
        super("not set");  // Assuming the color is set to "not set" initially
        this.isDrawFour = isDrawFour;
        this.gameManager = gameManager;
    }


    /**
     * Plays this wild card by interacting with the game management system to enforce its effects.
     *
     * @return true if the play is valid, otherwise false.
     */
    @Override
    public boolean playCard(Card topCard) {
        if (isDrawFour) {
            gameManager.nextPlayerDrawCards(4);
        }
        return true;
    }


    /**
     * Sets the color of this wild card.
     * This method is typically called when the card is played to choose the color dynamically.
     *
     * @param color The color to set for this card.
     */
    public void setColor(String color) {
        this.color = color;
    }


    /**
     * Determines if this wild card is a "Draw Four" card.
     *
     * @return true if this is a "Draw Four" card, false otherwise.
     */
    public boolean isDrawFour() {
        return isDrawFour;
    }


    @Override
    public String toString() {
        String cardType = isDrawFour ? "Wild Draw 4" : "Wild";
        return color.equals("Wild") ? cardType : cardType + " (" + color + ")";
    }
}
