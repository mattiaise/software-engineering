package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FlushEvaluator implements ChainedHandEvaluator{

    private final ChainedHandEvaluator next;

    public FlushEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isFlush(pHand) ? HandRank.FLUSH : next.HandEvaluator(pHand);
    }

    public static boolean isFlush(PokerHand pHand) {
        Map<Suit, Integer> map = new HashMap<>();
        for (Card card : pHand) map.merge(card.getSuit(), 1, Integer::sum);
        return map.containsValue(5);
    }
}
