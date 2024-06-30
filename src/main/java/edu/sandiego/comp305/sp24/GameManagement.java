package edu.sandiego.comp305.sp24;




import java.util.ArrayList;
import java.util.Deque;




/**
 * Manages the game logic and state for the game.
 * handles player turns, deck management, and the flow of the game
 */
public class GameManagement {
    private ArrayList<Player> players;
    private PlayingDeck playingDeck;
    private DrawingDeck drawingDeck;
    private Deque<Card> pile;
    private int currentPlayerIndex;
    private int turnStep;


    /**
     * Constructs a GameManagement instance with specified players, a deck, and a pile.
     * Initializes the game with a forward playing direction.
     *
     * @param players The list of players participating in the game.
     * @param deck The deck of cards to be used in the game.
     * @param pile The discard pile, initially empty.
     */
    public GameManagement(ArrayList<Player> players, Deck deck, Deque<Card> pile) {
        this.players = players;
        this.playingDeck = new PlayingDeck(this);
        this.drawingDeck = new DrawingDeck();
        this.pile = pile;
        this.currentPlayerIndex = 0;
        this.turnStep = 1;


    }


    /**
     * starts the game by shuffling the deck, dealing cards to players,
     * and placing the first card on the discard pile to begin play
     */
    public void startGame() {
        playingDeck.shuffle();
        dealCards();
        Card firstCard = playingDeck.draw();
        if (firstCard instanceof WildCard) {
            // replace starting with the wildcard with a red 2
            // because wildcard would not be set and could not be played on
            firstCard = new NumberCard("Red", 2);
        }
        pile.push(firstCard);
    }


    /**
     * deals seven cards to each player
     */
    private void dealCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(playingDeck.draw());
            }
        }
    }


    /**
     * checks if the game is over, which is true when any player has no cards left
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    /**
     * skips the next players turn by advancing two places in the player order
     */
    public void skipNextPlayer() {
        int nextPlayerIndex = calculateNextPlayerIndex(1);
        Player nextPlayer = players.get(nextPlayerIndex);
        System.out.println("\n" + nextPlayer.getName() + "'s turn is skipped!");
        advanceTurn(2);
    }


    /**
     * reverses the direction of play
     */
    public void reversePlayOrder() {
        turnStep = -turnStep;
    }


    /**
     * next player to draw a specified number of cards and advances the turn
     *
     * @param count number of cards to draw
     */
    public void nextPlayerDrawCards(int count) {
        Player nextPlayer = getNextPlayer();
        for (int i = 0; i < count; i++) {
            nextPlayer.addCard(playingDeck.draw());
        }
        System.out.println("\n" + count + " cards added to " + nextPlayer.getName() + "'s hand!");
        skipNextPlayer();
    }


    /**
     * get the next player in the turn order based on the current direction of play
     *
     * @return the next player.
     */
    private Player getNextPlayer() {
        int nextPlayerIndex = calculateNextPlayerIndex(1);
        return players.get(nextPlayerIndex);
    }


    /**
     * advances the turn by a specified number of steps
     *
     * @param steps number of steps to move in the player order
     */
    public void advanceTurn(int steps) {
        currentPlayerIndex = calculateNextPlayerIndex(steps);
    }


    /**
     * calculates the index of the player who will take the next turn
     *
     * @param steps number of steps to advance in the player list
     * @return the index of the next player
     */
    private int calculateNextPlayerIndex(int steps) {
        int nextIndex = (currentPlayerIndex + (steps * turnStep) + players.size()) % players.size();
        if (nextIndex < 0) {
            nextIndex += players.size();
        }
        return nextIndex;
    }


    /**
     * returns index of the current player in the turn order
     *
     * @return index of the current player
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }


    public Deck getDeck() {
        return playingDeck;
    }


    public Deque<Card> getPile() {
        return pile;
    }


    public boolean validatePlay(Card cardToPlay) {
        if (pile.isEmpty()) {
            return true;
        }
        Card topCard = pile.peek();
        return cardToPlay.playCard(topCard);
    }


    public String getWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player.getName();
            }
        }
        return null;
    }

    /**
     * @return true if the play direction is forward, false if reversed
     */
    public boolean getPlayDirection() {
        return turnStep > 0;
    }


}
