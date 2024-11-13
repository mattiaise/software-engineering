package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TwoPairsEvaluator implements ChainedHandEvaluator {

    private final ChainedHandEvaluator next;

    public TwoPairsEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isTwoPairs(pHand) ? HandRank.TWO_PAIR : next.HandEvaluator(pHand);
    }

    private boolean isTwoPairs(PokerHand pHand) {
        Map<Rank, Integer> map = new HashMap<>();
        for (Card card : pHand) map.merge(card.getRank(), 1, Integer::sum);
        return map.values().stream().filter(x -> x == 2).count() == 2;
    }
}
