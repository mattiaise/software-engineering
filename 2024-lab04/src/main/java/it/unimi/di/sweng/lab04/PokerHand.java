package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PokerHand implements Comparable<PokerHand>, Iterable<Card> {

    private final List<Card> cards;

    private static final ChainedHandEvaluator eval =
            new StraightFlushEvaluator(
                    new FourOfAKindEvaluator(
                            new FullHouseEvaluator(
                                    new FlushEvaluator(
                                            new StraightEvaluator(
                                                    new ThreeOfAKindEvaluator(
                                                            new TwoPairsEvaluator(
                                                                    new OnePairEvaluator(
                                                                            ChainedHandEvaluator.HIGH_CARD))))))));

    public PokerHand(List<Card> cards) {
        List<Card> lst = new ArrayList<>(cards);
        lst.sort(Comparator.comparing(Card::getRank));
        this.cards = lst;
    }

    public HandRank getRank() {
        return eval.HandEvaluator(this);
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(@NotNull PokerHand o) {
        return Integer.compare(this.getRank().ordinal(), o.getRank().ordinal());
    }

    int size() {
        return cards.size();
    }
}