package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayAlwaysFigureCard implements Strategy {

    private final Strategy next;

    public PlayAlwaysFigureCard(Strategy next) {
        this.next = next;
    }
    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card card : cards) {
            if (card.getRank().ordinal() > 6) return card;
        }
        return next.chooseCard(cards, attackCard);
    }
}
