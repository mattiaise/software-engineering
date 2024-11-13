package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class OnePairEvaluator implements ChainedHandEvaluator{

    private final ChainedHandEvaluator next;

    public OnePairEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isOnePair(pHand) ? HandRank.ONE_PAIR : next.HandEvaluator(pHand);
    }

    private boolean isOnePair(PokerHand pHand) {
        Map<Rank, Integer> map = new HashMap<>();
        for (Card card : pHand) map.merge(card.getRank(), 1, Integer::sum);
        return map.containsValue(2);
    }
}
