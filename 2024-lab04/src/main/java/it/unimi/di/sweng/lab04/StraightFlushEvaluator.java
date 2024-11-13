package it.unimi.di.sweng.lab04;

import org.jetbrains.annotations.NotNull;

import static it.unimi.di.sweng.lab04.FlushEvaluator.isFlush;
import static it.unimi.di.sweng.lab04.StraightEvaluator.isStraight;

public class StraightFlushEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public StraightFlushEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isStraightFlush(pHand) ? HandRank.STRAIGHT_FLUSH : next.HandEvaluator(pHand);
    }

    private boolean isStraightFlush(PokerHand pHand) {
        return (isStraight(pHand) && isFlush(pHand));
    }
}
