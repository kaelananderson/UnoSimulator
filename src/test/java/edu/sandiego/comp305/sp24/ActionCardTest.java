package edu.sandiego.comp305.sp24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ActionCardTest {
    private GameManagement gameManager;
    private ActionCard skipCard;
    private ActionCard reverseCard;
    private ActionCard draw2Card;
    private NumberCard mockTopCard;


    @BeforeEach
    void setup() {
        gameManager = mock(GameManagement.class); // Mock the game management to control game behavior
        skipCard = new ActionCard("Red", "Skip", gameManager);
        reverseCard = new ActionCard("Blue", "Reverse", gameManager);
        draw2Card = new ActionCard("Yellow", "Draw 2", gameManager);
        mockTopCard = new NumberCard("Red", 4);

    }

    // test initialization of the action cards
    @Test
    public void testConstructor() {
        assertEquals("Red", skipCard.getColor(), "Card color should be Red");
        assertEquals("Skip", skipCard.getActionType(), "Action type should be Skip");
    }

    // test the playCard method
    @Test
    public void testPlayCard() {
        // assert that
        assertTrue(skipCard.playCard(mockTopCard), "skipCard matches Red with top card so should play");

        // skip card is now the top card
        assertFalse(reverseCard.playCard(skipCard), "reverseCard does not match top card");

        // create a skip card
        ActionCard newSkipCard = new ActionCard("Green", "Skip", gameManager);

        // assert that a skip card plays on top of same action type
        assertTrue(newSkipCard.playCard(skipCard),"Skip card should play on top of another skip card");
    }

    // test that when skip card is played, it invokes the correct method in game manager
    @Test
    public void testPerformAction() {
        skipCard.performAction(gameManager);
        verify(gameManager).skipNextPlayer();

        reverseCard.performAction(gameManager);
        verify(gameManager).reversePlayOrder();

        draw2Card.performAction(gameManager);
        verify(gameManager).nextPlayerDrawCards(2);
    }

    // test the action type getter
    @Test
    public void testGetActionType() {
        assertEquals("Skip", skipCard.getActionType(), "getActionType should return 'Skip'");
    }

    // test string representation method
    @Test
    public void testToString() {
        assertEquals("Blue Reverse", reverseCard.toString(), "toString should return 'Blue Reverse'");
    }

}
