package edu.sandiego.comp305.sp24;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberCardTest {

    // test initialization of card
    @Test
    public void testConstructor() {
        NumberCard blueFour = new NumberCard("Blue", 4);
        assertEquals("Blue", blueFour.getColor(), "Card color should be Blue");
        assertEquals(4, blueFour.getNumber(), "Card number should be 4");
    }

    // test for playCard method
    @Test
    public void testPlayCardLogic() {
        NumberCard playingCard = new NumberCard("Blue", 4);
        NumberCard matchingColorCard = new NumberCard("Blue", 7);
        NumberCard matchingNumberCard = new NumberCard("Green", 4);
        NumberCard nonMatchingCard = new NumberCard("Red", 5);

        // assert color matches
        assertTrue(playingCard.playCard(matchingColorCard), "Should return true when colors match");

        // assert number matches
        assertTrue(playingCard.playCard(matchingNumberCard), "Should return true when numbers match");

        // assert no match
        assertFalse(playingCard.playCard(nonMatchingCard), "Should return false when neither color nor number matches");
    }

    // test making multiple number cards
    @Test
    public void testDifferentCards() {
        NumberCard yellowFive = new NumberCard("Yellow", 5);
        NumberCard blueThree = new NumberCard("Blue", 3);

        assertEquals("Yellow", yellowFive.getColor(), "Card color should be Yellow");
        assertEquals(5, yellowFive.getNumber(), "Card number should be 5");
        assertEquals("Blue", blueThree.getColor(), "Card color should be Blue");
        assertEquals(3, blueThree.getNumber(), "Card number should be 3");
    }

    // test string representation method
    @Test
    public void testToString() {
        NumberCard blueFour = new NumberCard("Blue", 4);
        assertEquals("Blue 4", blueFour.toString(), "toString should return 'Blue 4'");
    }
}
