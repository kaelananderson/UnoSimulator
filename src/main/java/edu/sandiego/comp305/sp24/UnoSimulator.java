package edu.sandiego.comp305.sp24;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

public class UnoSimulator {
    private GameManagement gameManagement;
    private Scanner scanner;
    private boolean skipNextTurn = false;


    public UnoSimulator() {
        scanner = new Scanner(System.in);
    }


    public void startGame() {
        System.out.println("*** Welcome to Uno Game Simulator! ***");
        int numPlayers = getNumberOfPlayers();
        ArrayList<Player> players = getPlayers(numPlayers);
        Deck deck = new PlayingDeck(gameManagement);
        deck.shuffle();


        gameManagement = new GameManagement(players, deck, new LinkedList<>());
        gameManagement.startGame();


        // main game loop
        while (!gameManagement.isGameOver()) {
            playRound(players);
        }


        // print winner's name
        String winner = gameManagement.getWinner();
        System.out.println("\n***\n\nCongratulations, " + winner + "! You have won the game!");
        System.out.println("\nGame over! Thanks for playing.\n\n***");
    }


    private void playRound(ArrayList<Player> players) {
        if (gameManagement.isGameOver()) return;


        int currentPlayerIndex = gameManagement.getCurrentPlayerIndex();
        Player player = players.get(currentPlayerIndex);


        playTurn(player);


        // if the player has only one card left print Uno
        if (player.getHand().size() == 1) {
            System.out.println("\n***" + player.getName() + " says: Uno! ***");
        }


        if (!gameManagement.isGameOver()) {
            if (skipNextTurn) {
                skipNextTurn = false;
            } else {
                gameManagement.advanceTurn(1);
            }
        }
    }


    private int getNumberOfPlayers() {
        System.out.println("\nEnter number of players (2-4):");
        int numPlayers;
        while (true) {
            numPlayers = scanner.nextInt();
            if (numPlayers >= 2 && numPlayers <= 4) {
                break;
            }
            System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
        }
        scanner.nextLine();
        return numPlayers;
    }


    private ArrayList<Player> getPlayers(int numPlayers) {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\nEnter name for Player " + (i + 1) + ":");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
        return players;
    }


    private void playTurn(Player player) {

        //clearConsole();

        // skip the player if skipNextTurn is true and reset skipNextTurn
        if (skipNextTurn) {
            skipNextTurn = false;
            gameManagement.advanceTurn(1);
            return;
        }


        System.out.println("\n***\nPASS COMPUTER TO " + player.getName() + "\n***");
        System.out.println("\n" + player.getName() + "'s turn. Press Enter to continue...");
        scanner.nextLine();


        System.out.println("Current hand:\n");
        player.printHand();


        System.out.println("\nTop card on the pile: " + gameManagement.getPile().peek().toString());


        boolean turnOver = false;
        while (!turnOver) {
            System.out.println("\nChoose action:\n1) Play card\n2) Draw card");
            int choice = scanner.nextInt();
            scanner.nextLine();


            if (choice == 1) {
                turnOver = attemptToPlayCard(player);
            } else if (choice == 2) {
                player.addCard(gameManagement.getDeck().draw());
                turnOver = true;
            } else {
                System.out.println("\nInvalid choice. Please select a valid option.");
            }
        }
    }


    private boolean attemptToPlayCard(Player player) {
        try {
            System.out.println("\nEnter the index of the card you want to play:");
            int cardIndex = scanner.nextInt();
            scanner.nextLine();


            Card cardToPlay = player.getCardAtIndex(cardIndex);
            if (gameManagement.validatePlay(cardToPlay)) {
                player.playCard(cardIndex);
                gameManagement.getPile().push(cardToPlay);


                // handle action cards
                if (cardToPlay instanceof ActionCard) {
                    ActionCard actionCard = (ActionCard) cardToPlay;


                    if ("Skip".equals(actionCard.getActionType())) {
                        System.out.println("\nSkip card played!");
                        skipNextTurn = true;


                    } else if ("Reverse".equals(actionCard.getActionType())) {
                        System.out.println("\nReverse card played!");


                    } else if ("Draw 2".equals(actionCard.getActionType())) {
                        System.out.println("\nDraw 2 card played!");
                        skipNextTurn = true;
                    }
                }


                // handle wild cards
                if (cardToPlay instanceof WildCard) {
                    WildCard wildCard = (WildCard) cardToPlay;
                    System.out.println("\nWild card played! Choose a color (Red, Yellow, Blue, Green):");
                    String chosenColor = scanner.nextLine();
                    wildCard.setColor(chosenColor);


                    if (wildCard.isDrawFour()) {
                        skipNextTurn = true;
                    }
                }
                return true;
            } else {
                System.out.println("Invalid card play. Try a different card or draw.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid card index. Try again.");
            scanner.nextLine();
            return false;
        }
    }


    public static void clearConsole() {
        // print 50 newlines to simulate clearing the console
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    public static void main(String[] args) {
        UnoSimulator simulator = new UnoSimulator();
        simulator.startGame();
    }
}




