package it.unimi.di.sweng.lab04;

import org.jetbrains.annotations.NotNull;

public interface ChainedHandEvaluator {
    HandRank HandEvaluator(PokerHand pHand);
    ChainedHandEvaluator HIGH_CARD = (@NotNull PokerHand hand) -> HandRank.HIGH_CARD;
}
