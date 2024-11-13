package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;

public class MyDeck implements CountableDeck {

    private final Deck adaptee = new Deck();
    private int size = 52;

    @Override
    public Card draw() {
        size--;
        return adaptee.draw();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return adaptee.isEmpty();
    }
}
