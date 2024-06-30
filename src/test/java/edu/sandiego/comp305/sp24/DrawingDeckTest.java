package edu.sandiego.comp305.sp24;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class DrawingDeckTest {
    private DrawingDeck deck;

    @BeforeEach
    void setUp() {
        deck = new DrawingDeck();
    }

    @Test
    void testAddCard() {
        Card card = new NumberCard("Blue", 7);
        deck.addCard(card);

        assertEquals(1, deck.getDeckSize(), "Deck should contain one card after adding");
    }

   @Test
    void testDraw() {
        NumberCard expectedCard = new NumberCard("Red", 5);
        deck.addCard(expectedCard);
        Card drawnCard = deck.draw();

        assertSame(expectedCard, drawnCard, "the drawn card should be the one that was added");
    }

    @Test
    void testDrawFromEmptyDeck() {
        assertTrue(deck.isDeckEmpty(), "Deck should be empty initially");
        assertThrows(IllegalArgumentException.class, () -> deck.draw(), "Should throw exception when drawing from an empty deck");
    }

    @Test
    void testShuffle() {
        deck.addCard(new NumberCard("Red", 1));
        deck.addCard(new NumberCard("Blue", 2));
        deck.addCard(new NumberCard("Green", 3));
        deck.addCard(new NumberCard("Yellow", 4));

        List<Card> originalOrder = new ArrayList<>();
        while (!deck.isDeckEmpty()) {
            originalOrder.add(deck.draw());
        }
        originalOrder.forEach(deck::addCard);
        deck.shuffle();

        List<Card> shuffledOrder = new ArrayList<>();
        while (!deck.isDeckEmpty()) {
            shuffledOrder.add(deck.draw());
        }

        assertEquals(originalOrder.size(), shuffledOrder.size(), "Shuffled deck should contain the same cards as the original deck");
        assertNotEquals(originalOrder, shuffledOrder, "The order of the cards should be different after shuffling");
       }
}