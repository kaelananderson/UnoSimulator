package edu.sandiego.comp305.sp24;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;


class PlayingDeck extends Deck {
    private Deque<Card> cards;
    private GameManagement gameManager;


    public PlayingDeck(GameManagement gameManager) {
        this.gameManager = gameManager;
        this.cards = new ArrayDeque<>();
        initializeDeck();
    }


    // method to build the deck
    private void initializeDeck() {
        String[] colors = {"Red", "Yellow", "Blue", "Green"};


        // number and action cards for each color
        for (String color : colors) {
            // only one 0 card for each color
            deck.add(new NumberCard(color, 0));


            // two sets of 1-9 cards for each color
            for (int i = 1; i <= 9; i++) {
                deck.add(new NumberCard(color, i));
                deck.add(new NumberCard(color, i));
            }


            // two of each action card for each color
            for (int i = 0; i < 2; i++) {
                deck.add(new ActionCard(color, "Draw 2", gameManager));
                deck.add(new ActionCard(color, "Reverse", gameManager));
                deck.add(new ActionCard(color, "Skip", gameManager));
            }
        }


        // wild cards
        for (int i = 0; i < 4; i++) {
            deck.add(new WildCard(false, gameManager)); // Wild
            deck.add(new WildCard(true, gameManager));  // Wild Draw 4
        }


        shuffle();
    }


    @Override
    public void shuffle() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Cannot shuffle an empty deck");
        }
        ArrayList<Card> list = new ArrayList<>(deck);
        Collections.shuffle(list);
        deck.clear();
        deck.addAll(list);
    }


    @Override
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Cannot draw from an empty deck");
        }
        return deck.pollFirst();
    }


    @Override
    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card to the deck");
        }
        // Adds cards to the bottom of the deck
        deck.addLast(card);
    }


    public Card getTopCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("No cards in the deck to view the top card.");
        }
        return deck.peekFirst();
    }


    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }
}
