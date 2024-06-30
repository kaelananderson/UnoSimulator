package edu.sandiego.comp305.sp24;

import java.util.ArrayDeque;
import java.util.Deque;

abstract class Deck {
    protected Deque<Card> deck = new ArrayDeque<>();

    public abstract void shuffle();

    public abstract Card draw();

    public abstract void addCard(Card card);
}