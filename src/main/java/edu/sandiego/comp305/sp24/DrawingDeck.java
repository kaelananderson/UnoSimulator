package edu.sandiego.comp305.sp24;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

class DrawingDeck extends Deck {

    private Deque<Card> deck = new ArrayDeque<>();

    @Override
    public void shuffle() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("Cannot shuffle an empty deck");
        }
        ArrayList<Card> list = new ArrayList<>(deck);
        Collections.shuffle(list);
        deck.clear();
        deck.addAll(list);
    }

    @Override
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("Cannot draw from an empty deck");
        }
        // Retrieves and removes the first card
        return deck.pollFirst();
    }

    @Override
    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card to the deck");
        }
        deck.addLast(card);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }
}