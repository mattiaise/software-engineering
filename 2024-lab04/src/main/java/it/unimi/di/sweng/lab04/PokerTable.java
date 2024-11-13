package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerTable implements Iterable<PokerHand> {

    private final Deck deck;

    private final List<PokerHand> players = new ArrayList<>();


    PokerTable(Deck deck, int players) {

        this.deck = deck;

        for (int i = 0; i < players; i++) {
            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < 5; j++) cards.add(deck.draw());
            this.players.add(new PokerHand(cards));
        }

    }
    public PokerTable(int players) {

        this.deck = new Deck();

        for (int i = 0; i < players; i++) {
            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < 5; j++) cards.add(deck.draw());
            this.players.add(new PokerHand(cards));
        }
    }

    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return players.iterator();
    }


    public PokerHand getHand(int player) {
        List<Card> aux = new ArrayList<>();
        players.get(player).forEach(aux::add);
        return new PokerHand(aux);
    }

    public void change(int player, List<Card> toChange) {

    }
}
