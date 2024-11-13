package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.Partita;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER;
import static org.mockito.Mockito.*;

@Timeout(2)
public class GiocatoreTest {

    @Test
    void iterableTest() {
        Giocatore g = new Giocatore("g", mock(Partita.class));
        List<Card> cards = List.of(
                Card.get(Rank.FIVE, Suit.DIAMONDS),
                Card.get(Rank.ACE, Suit.DIAMONDS));

        for (Card card : cards) g.daiCarta(card);

        assertThat(g).containsExactlyInAnyOrderElementsOf(cards);
    }

    @Test
    void testTurno(){
        Partita mockPartita = mock(Partita.class);
        Giocatore giocatore = new Giocatore("Andrea", mockPartita);

        when(mockPartita.giocaCarta(giocatore, Card.get(Rank.TEN, Suit.CLUBS))).thenReturn(2);

        giocatore.daiCarta(Card.get(Rank.TEN, Suit.CLUBS));
        giocatore.turno();

        verify(mockPartita).giocaCarta(giocatore, Card.get(Rank.TEN, Suit.CLUBS));
        AssertionsForClassTypes.assertThat(giocatore)
                .extracting("punti").isEqualTo(2);
    }
}
