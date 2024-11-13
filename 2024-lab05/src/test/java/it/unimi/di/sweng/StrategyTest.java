package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.Partita;
import it.unimi.di.sweng.rubamazzetto.Strategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Timeout(2)
public class StrategyTest {

    @Test
    void nullObjTest() {
        Giocatore g = mock(Giocatore.class);
        MockUtils.whenIterated(g, Card.get(Rank.ACE, Suit.DIAMONDS), Card.get(Rank.FIVE, Suit.CLUBS));
        Strategy nullObject = Strategy.FIRST_CARD;
        assertThat(nullObject.chooseCard(mock(Partita.class), g)).isEqualTo(Card.get(Rank.ACE, Suit.DIAMONDS));
    }
}
