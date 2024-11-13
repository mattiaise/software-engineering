package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FourOfAKindEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public FourOfAKindEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isFullHouse(pHand) ? HandRank.FOUR_OF_A_KIND : next.HandEvaluator(pHand);
    }

    private boolean isFullHouse(PokerHand pHand) {
        Map<Rank, Integer> map = new HashMap<>();
        for (Card card : pHand) map.merge(card.getRank(), 1, Integer::sum);
        return map.containsValue(4);
    }
}
