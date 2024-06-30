# Your Design

---
- What classes do I need? Would it be helpful to have any interfaces or abstract classes and for what?

    - TODO: fill in your classes, interfaces, abstract classes

    - A card class
    - numberCard class
    - actionCard class
    - wildCard class
    - A player class
    - A abstract Deck class
    - playing deck class
    - drawing deck class
    - A GameManagement class that controls the flow of the game (manages players and decks etc.)
    - A HandOfCards class
    - A DiscardPile class
    - A Playable interface

---

## For each Interface

- What are the promised actions (methods) of anything that implements this interface?
- What are the parameters needed?
- What will they return?

    - PlayableInterface
        - methods
            - PlayCard(Card card) returns void
            - DrawCard() returns Card

    - PlayerInterface?


---

## For each Abstract class

- Does this class need to implement any interfaces or extend any classes? Any you wrote or others.
- What is the information (instance variables) that this class has that will be similar across child classes?
- What are the types, should they have any default values?
- What are the promised actions (methods) for this class?
- What are the parameters needed?
- What will they return?
- Which of these should be concrete and which abstract?

    - Fill in the above questions for each interface

    - AbstractPlayer class:
        - Implements PlayerInterface?
        - Instance variables:
            - String name  , defaults to empty string
            - List hand    , defaults to empty list
            - boolean isTurn, true or false if it is their turn
        - Methods
            - getName() Returns string of players name
            - getHand() Returns List of players hand
            - addCard(Card card) Returns void
              They will all be concrete

    - Card class 
      - Instance variables
        - String color, color of the card
      - Methods
        - getColor
        - boolean playCard() , Plays the card, returns true if play is successful.

    - Deck class 
      - instance variables
        - Deque<Card> deck: A double-ended queue of cards, representing the deck.
      - methods 
        - abstract void shuffle(): Shuffles the deck to randomize card order.
          abstract Card draw(): Draws a card from the deck.
          abstract void addCard(Card card): Adds a card to the deck.
      - returns
          - draw(): Returns a Card.

---

## For each class

- Does this class need to implement any interfaces or extend any classes? Any you wrote or others.
- What is the information (instance variables) that this class has?
- What are the types?
- What are the actions (methods) for this class? Which of these methods do other classes need access to?
- What are the parameters needed?
- What will they return?

    - Fill in the above questions for each interface

    - NumberCard class
      - extends card
      - Instance variables
        - String color
      - Methods 
        - boolean playCard()

      - ActionCard class
          - extends card
          - Instance variables
              - String actionType, type of action (skip, reverse, draw 2)
          - Methods
              - boolean playCard()
              - skip method that skips the next person
              - reverse method that switches the order of who is next
              - draw2 method that also skips the person they drew 2

      - WildCard class
          - extends card
          - Instance variables
              - boolean isDrawFour, for if the wildcard is regular or draw 4
          - Methods
              - boolean playCard(), need to prompt user which color to select

      - Player class
          - Instance variables
              - String name
              - List hand
          - Actions
              - Constructor to initialize the player with a name and an empty hand
              - Getter methods for name and hand
              - Method to add a card to the player's hand
                Returns void for adding a card, a String for name and a List for Hand getters

      - GameManagement class
          - Instance variables
              - players (ArrayList<Player>)
              - deck (Deck)
              - pile (Pile)
          - Actions
              - Constructor to initialize the game with players, deck, pile
              - Method to start the game
              - Method to check if the game is over
                Returns void for all methods

      - HandOfCards class
          - Instance variables
              - cards (ArrayList<Card>)
          - Actions
              - Constructor to initialize an empty hand
              - Method to add a card to the hand
              - Method to remove a card from the hand
                Returns void for adding and removing a card

      - PlayingDeck class
        - extends Deck class
        - Instance variables
          - Deque<Card> deck: Stack of cards.
          - Card topCard: The current top card of the playing deck.
        - Methods 
          - void shuffle(): Shuffles the deck.
          - Card getTopCard(): Draws the top card and updates the topCard attribute. Returns the Card that was on top.
          - void addCard(Card card): Adds a card to the bottom of the deck (discard pile).

      - DrawingDeck class
        - extends Deck class
        - Instance variables
          - Deque<Card> deck: The drawing deck.
        - Methods:
          - void shuffle(): Shuffles the deck.
          - Card draw(): Draws a card from the top of the drawing deck. Returns the drawn Card.
          - void addCard(Card card): Adds a card to the bottom of the deck.

---
## Testing for each method


- What are your test cases that you need and what inputs and outputs will you test for each?

    - TODO: fill in your testing plan

    - Card:
        - Test card creation for different colors and values
        - Test for all the different kinds of cards as well

    - Player:
        - Test player creation and adding 7 cards to the players hand

    - Deck Testing
      - Deck.shuffle()
        - Test Cases:
            - Shuffles a deck of cards and checks randomness.
        - Expected Output:
            - The deck order changes significantly after shuffling.
      - Deck.draw()
        - Test Cases:
            - Draws a card from a deck.
            - Draws from an empty deck.
        - Expected Output:
            - The deck size decreases, and the drawn card matches expected attributes.
            - An appropriate exception is thrown when drawing from an empty deck.
      - Deck.addCard(Card card)
        - Test Cases:
            - Add a card to the deck.
        - Expected Output:
            - The deck size increases, and the added card appears in the expected position.

    - GameManagement:
        - Test game initialization and checking game over condition

    - HandOfCards:
        - Test adding and removing cards from the hand

    - DiscardPile:
        - Test adding cards to the pile and getting the top card
