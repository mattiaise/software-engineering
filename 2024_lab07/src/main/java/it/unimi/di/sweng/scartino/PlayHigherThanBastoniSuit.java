package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayHigherThanBastoniSuit implements Strategy {

    private final Strategy next;

    public PlayHigherThanBastoniSuit(Strategy next) {
        this.next = next;
    }
    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card card : cards) {
            if (card.getSuit().ordinal() > 1) return card;
        }
        return next.chooseCard(cards, attackCard);
    }
}
