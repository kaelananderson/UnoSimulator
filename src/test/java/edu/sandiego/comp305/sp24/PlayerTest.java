package edu.sandiego.comp305.sp24;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlayerTest {

    private Player player;
    private Card card1;
    private Card card2;

    @BeforeEach
    public void setUp() {
        player = new Player("Kyle");
        card1 = new Card("Red");
        card2 = new Card("Blue");
        player.addCard(card1);
        player.addCard(card2);
    }

    // Test to verify getName method return the correct name
    @Test
    public void testGetName() {
        assertEquals("Kyle", player.getName());
    }


    //Test to verify addCard adds cards to the players hand and that getHand returns the correct list of cards
    @Test
    public void testAddCardAndGetHand() {

        // Check if the number of cards in the hand is correct
        assertEquals(2, player.getHand().size());

        // Check if both cards are in the hand
        assertTrue(player.getHand().contains(card1));
        assertTrue(player.getHand().contains(card2));
    }

    // test to verify a valid index is selected for playCard
    @Test
    public void testPlayCardValidIndex() {

        Card playedCard = player.playCard(0);

        assertEquals(card1, playedCard, "The card at index 0 should be played and removed from the hand");
        assertEquals(1, player.getHand().size(), "The hand size should be 1 after playing a card");
        assertEquals(card2, player.getHand().get(0), "The remaining card should be card2");
    }

    // test to verify a negative index throws error
    @Test
    public void testPlayCardInvalidIndexNegative() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            player.playCard(-1);
        });

        // card with negative index should throw out of bounds error
        assertEquals("Invalid card index", exception.getMessage(), "Playing a card with a negative index should throw an IndexOutOfBoundsException");
    }

    // test to verify an index out of bounds throws error
    @Test
    public void testPlayCardInvalidIndexOutOfBounds() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            player.playCard(2);
        });

        // card with index out of bounds should throw error
        assertEquals("Invalid card index", exception.getMessage(), "Playing a card with an out-of-bounds index should throw an IndexOutOfBoundsException");
    }


    // Test to verify that drawing a card adds it to the player's hand
    @Test
    public void testDrawCard() {
        Player player = new Player("David");
        Card card = new Card("Green");


        player.drawCard(card);
        assertEquals(1, player.getHand().size());
        assertTrue(player.getHand().contains(card));
    }
}
