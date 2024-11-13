package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


public class StraightEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public StraightEvaluator(@NotNull ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank HandEvaluator(PokerHand pHand) {
        return isStraight(pHand) ? HandRank.STRAIGHT : next.HandEvaluator(pHand);
    }

    public static boolean isStraight(PokerHand pHand) {
        Iterator<Card> iterator = pHand.iterator();
        Card previousCard = iterator.next();
        while (iterator.hasNext()) {
            Card currentCard = iterator.next();
            if (currentCard.getRank().ordinal() != previousCard.getRank().ordinal() + 1)
                return false;
            previousCard = currentCard;
        }
        return true;
    }
}
