package edu.sandiego.comp305.sp24;




/**
 * ActionCards like Skip, Reverse, and Draw Two
 * interacts with the GameManagement class
 * extends Card
 */
class ActionCard extends Card {
    protected String actionType;
    private GameManagement gameManager;




    /**
     * Constructs ActionCard with color and action type
     *
     * @param color color of card
     * @param actionType type of action this card performs
     */
    public ActionCard(String color, String actionType, GameManagement gameManager) {
        super(color);
        this.actionType = actionType;
        this.gameManager = gameManager;


    }




    /**
     * the action associated with this card
     *
     * @param game game management object that controls the state of the game
     */
    public void performAction(GameManagement game) {
        switch (actionType) {
            case "Skip":
                skip(game);
                break;
            case "Reverse":
                reverse(game);
                break;
            case "Draw 2":
                draw2(game);
                break;
            default:
                break;
        }
    }




    /**
     * plays card by checking if color or action type matches the top card
     *
     * @param topCard card currently on top of the play deck
     * @return true if the play is valid
     */
    @Override
    public boolean playCard(Card topCard) {
        if (this.color.equals(topCard.getColor()) || this.actionType.equals(((ActionCard) topCard).actionType)) {
            this.performAction(gameManager);
            return true;
        } else {
            return false;
        }
    }




    /**
     * executes the Skip action by skipping the next players turn
     *
     * @param game game management object
     */
    private void skip(GameManagement game) {
        game.skipNextPlayer();
    }




    /**
     * executes the Reverse action by reversing the order of players
     *
     * @param game game management object
     */
    private void reverse(GameManagement game) {
        game.reversePlayOrder();
    }




    /**
     * executes the Draw Two action by making the next player draw two cards
     *
     * @param game game management object
     */
    private void draw2(GameManagement game) {
        game.nextPlayerDrawCards(2);
    }


    public String getActionType() {
        return this.actionType;
    }


    @Override
    public String toString() {
        return color + " " + actionType;
    }
}
