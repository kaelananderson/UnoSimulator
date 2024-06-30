package edu.sandiego.comp305.sp24;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;


public class GameManagementTest {
    private GameManagement gameManager;
    private ArrayList<Player> players;
    private Deck deck;
    private Deque<Card> pile;


    @BeforeEach
    void setup() {
        // initialize mocks and actual objects
        players = new ArrayList<>();
        deck = Mockito.mock(Deck.class);
        pile = new LinkedList<>();
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        players.addAll(Arrays.asList(player1, player2));


        // setup behaviors for our decks
        when(deck.draw()).thenReturn(mock(Card.class));
        gameManager = new GameManagement(players, deck, pile);
    }

    // test that when startGame is called it deals 7 cards to each player
    @Test
    public void startGameDealCards() {
        gameManager.startGame();

        // each player should have 7 cards
        for (Player player : players) {
            verify(player, times(7)).addCard(any(Card.class));
        }

        assertFalse(pile.isEmpty(), "Pile should start with a top card");
    }


    // test that the game ends when a player's hard is empty
    @Test
    public void gameOverIfPlayerHasNoCards() {
        // player1 has no cards in their hand
        when(players.get(0).getHand()).thenReturn(new ArrayList<>());
        assertTrue(gameManager.isGameOver(), "Game should be over because one player has empty hand");
    }


    // test that as long as players have cards, game is not over
    @Test
    public void gameNotOverIfPlayersHaveCards() {
        // player1 has one mocked card in hand
        when(players.get(0).getHand()).thenReturn(Arrays.asList(mock(Card.class)));


        // player2 has two mocked cards in hand
        when(players.get(1).getHand()).thenReturn(Arrays.asList(mock(Card.class), mock(Card.class)));


        assertFalse(gameManager.isGameOver(), "Game should not be not over because players have cards in hand");
    }


    // test to verify skip action skips the next player
    @Test
    public void skipNextPlayerAdvanceTwoPlaces() {
        gameManager.skipNextPlayer();

        // current player is 2 more than current, meaning a single player was skipped
        assertEquals(2 % players.size(), gameManager.getCurrentPlayerIndex(), "CurrentPlayerIndex should be 2 more than current");
    }


    // test to verify reverse action changes direction
    @Test
    public void reversePlayOrderChangesDirection() {
        boolean initialDirection = gameManager.getPlayDirection();
        gameManager.reversePlayOrder();

        // assert the direction changes when reverse is called
        assertNotEquals(initialDirection, gameManager.getPlayDirection(), "Direction should be switched");
    }


    // test to ensure that when a player plays draw 2, 2 cards are added and next player is skipped
    // by testing getCurrentPlayerIndex, also tests the calculateNextPlayerIndex
    @Test
    public void nextPlayerDrawCardsAndAdvanceTurn() {
        gameManager.nextPlayerDrawCards(2);

        // next player should draw 2 cards
        verify(players.get(1), times(2)).addCard(any(Card.class));


        // current player is 2 more than current, meaning a single player was skipped after draw 2
        assertEquals(2 % players.size(), gameManager.getCurrentPlayerIndex(), "CurrentPlayerIndex should be 2 more than current");


        // current player should be back to 0, or player1
        assertEquals(0, gameManager.getCurrentPlayerIndex(), "Current player should be player 1 or index 0");


    }

    // test for the validatePlay method testing a valid card to be played
    @Test
    public void testValidatePlayValidCard() {

        NumberCard blueFour = new NumberCard("Blue", 4);
        NumberCard redFour = new NumberCard("Red", 4);

        pile.push(blueFour);

        boolean result = gameManager.validatePlay(redFour);

        assertTrue(result, "The card should be valid based on the top card of the pile");
    }

    // test the validatePlay method testing an invalid card to be played
    @Test
    public void testValidatePlayInvalidCard() {
        NumberCard blueFour = new NumberCard("Blue", 4);
        NumberCard redFive = new NumberCard("Red", 5);

        pile.push(blueFour);

        boolean result = gameManager.validatePlay(redFive);

        assertFalse(result, "The card should be invalid based on the top card of the pile");
    }

    @Test
    public void testGetWinner() {

        Player winner = players.get(0);
        // mock player having an empty hand
        when(winner.getHand()).thenReturn(Collections.emptyList());

        // mock other player has a card still
        when(players.get(1).getHand()).thenReturn(Arrays.asList(mock(Card.class)));

        String winnerName = gameManager.getWinner();

        // Assert
        assertEquals(winner.getName(), winnerName, "The winner should be the player with an empty hand");
    }
}
