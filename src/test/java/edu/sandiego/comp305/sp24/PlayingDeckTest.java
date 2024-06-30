package edu.sandiego.comp305.sp24;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


class PlayingDeckTest {
    private PlayingDeck deck;
    private GameManagement gameManger;


    @BeforeEach
    void setUp() {
        deck = new PlayingDeck(gameManger);
    }

    // test that deck was initialized with correct number of cards
    @Test
    public void testInitializeDeck() {
        // check that the deck has the correct number of cards
        assertEquals(108, deck.deck.size(), "Deck should have 108 cards after initialization");

        // count the number of cards of each type
        long numberCards = deck.deck.stream().filter(card -> card instanceof NumberCard).count();
        long actionCards = deck.deck.stream().filter(card -> card instanceof ActionCard).count();
        long wildCards = deck.deck.stream().filter(card -> card instanceof WildCard).count();

        assertEquals(76, numberCards, "Deck should have 76 number cards");
        assertEquals(24, actionCards, "Deck should have 24 action cards");
        assertEquals(8, wildCards, "Deck should have 8 wild cards");
    }

    @Test
    void testAddCard() {
        Card card = new NumberCard("Red", 5);
        deck.addCard(card);


        assertEquals(card, deck.deck.peekLast(), "The added card should be the bottom card of the deck");
    }


    @Test
    void testDraw() {
        Card topCard = deck.deck.peekFirst();

        Card drawnCard = deck.draw();

        assertEquals(topCard, drawnCard, "Drawn card should be the top card of the deck");
        assertEquals(107, deck.deck.size(), "Deck should have 107 cards after drawing one");
    }


    @Test
    public void testShuffle() {
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
        assertNotEquals(originalOrder,shuffledOrder, "The order of the cards should be different after shuffling");
    }


    @Test
    void testShuffleEmptyDeckThrowsException() {
        deck.deck.clear();

        assertThrows(IllegalStateException.class, () -> deck.shuffle(), "Shuffling an empty deck should throw an IllegalStateException");
    }


    @Test
    void testGetTopCard() {
        Card topCard = deck.deck.peekFirst();

        assertSame(topCard, deck.getTopCard(), "getTopCard should return the last card added");
    }


    @Test
    void testIsDeckEmptyInitially() {
        deck.deck.clear();

        assertTrue(deck.isDeckEmpty(), "Deck should be empty initially");
    }


    @Test
    void testIsDeckEmptyAfterAddingCards() {
        deck.addCard(new NumberCard("Blue", 7));
        assertFalse(deck.isDeckEmpty(), "Deck should not be empty after adding a card");
    }


    @Test
    void testIsDeckEmptyAfterRemovingAllCards() {
        deck.deck.clear();

        assertTrue(deck.isDeckEmpty(), "Deck should be empty after removing the only card");
    }


    @Test
    void testIsDeckEmptyAfterShuffleWithCards() {
        deck.addCard(new NumberCard("Blue", 7));
        deck.addCard(new NumberCard("Red", 5));
        deck.shuffle();
        assertFalse(deck.isDeckEmpty(), "Deck should not be empty after shuffling with cards in it");
    }
}
