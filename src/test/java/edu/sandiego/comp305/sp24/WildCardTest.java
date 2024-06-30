package edu.sandiego.comp305.sp24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WildCardTest {
    private GameManagement gameManagerMock;
    private WildCard wildCard;
    private WildCard drawFourWildCard;
    private NumberCard mockTopCard;

    @BeforeEach
    public void setup() {
        gameManagerMock = Mockito.mock(GameManagement.class);

        wildCard = new WildCard(false, gameManagerMock);
        drawFourWildCard = new WildCard(true, gameManagerMock);

        // initialize a mockTopCard
        mockTopCard = new NumberCard("Yellow", 4);
    }

    // test initialization
    @Test
    public void testConstructor() {
        System.out.println(wildCard.isDrawFour());
        assertFalse(wildCard.isDrawFour(), "Regular WildCard should not be a Draw 4");
        assertTrue(drawFourWildCard.isDrawFour(), "DrawFour WildCard should be a Draw 4");
    }

    // test choosing the color of the wildcard
    @Test
    public void testSetColor() {
        wildCard.setColor("Green");
        assertEquals("Green", wildCard.getColor(), "Wildcard color should be Green");
    }

    // test the play card logic and interaction with game management
    @Test
    public void testPlayCard() {
        assertTrue(drawFourWildCard.playCard(mockTopCard), "playCard should return true");
        // verify the game is invoked for draw four card
        verify(gameManagerMock, times(1)).nextPlayerDrawCards(4);
    }

    // Test that draw4 method works
    @Test
    public void testDrawFourBehavior() {
        drawFourWildCard.playCard(mockTopCard);
        // verify the game correctly handles draw four action
        verify(gameManagerMock).nextPlayerDrawCards(4);
    }

    // test string representation method
    @Test
    public void testToString() {
        assertEquals("Wild (not set)", wildCard.toString(), "toString should return 'Wild (not set)'");

        // see string after a color is set
        drawFourWildCard.setColor("Green");
        assertEquals("Wild Draw 4 (Green)", drawFourWildCard.toString(), "toString should return '(Wild Draw 4 Green)'");


    }
}