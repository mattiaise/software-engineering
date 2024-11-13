package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

public interface Strategy {

    @NotNull Card chooseCard(@NotNull Partita p, @NotNull Giocatore g);

    Strategy FIRST_CARD = (@NotNull Partita p, @NotNull Giocatore g) -> g.iterator().next();
}
