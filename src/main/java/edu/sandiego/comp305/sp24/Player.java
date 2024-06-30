package edu.sandiego.comp305.sp24;


import java.util.ArrayList;
import java.util.List;


class Player {
    private String name;
    private List<Card> hand;


    public Player(String name) {


        this.name = name;
        this.hand = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Invalid card index");
        }
        return hand.remove(index);
    }

    public void drawCard(Card card){
        hand.add(card);
    }

    public Card getCardAtIndex(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Invalid card index");
        }
        return hand.get(index);
    }

    public void printHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i).toString());
        }
    }

}
