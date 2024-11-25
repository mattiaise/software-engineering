package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayAlwaysRankFiveStrategy implements Strategy {

    private final Strategy next;

    public PlayAlwaysRankFiveStrategy(Strategy next) {
        this.next = next;
    }
    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card card : cards) {
            if (card.getRank().equals(Rank.CINQUE)) return card;
        }
        return next.chooseCard(cards, attackCard);
    }
}
