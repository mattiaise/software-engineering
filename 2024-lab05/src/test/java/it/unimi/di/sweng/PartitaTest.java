package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.MyDeck;
import it.unimi.di.sweng.rubamazzetto.Partita;
import it.unimi.di.sweng.rubamazzetto.Tavolo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Timeout(2)
public class PartitaTest {

    @Test
    void testControllaCartaSuTavolo() {
        try (var mockTavolo = Mockito.mockConstruction(Tavolo.class,
                (mock, context) -> when(mock.inMostra(Card.get(Rank.FIVE, Suit.CLUBS))).thenReturn(true))) {
            Partita p = new Partita();

            assertThat(p.controllaSeCartaPresenteSuTavolo(Card.get(Rank.FIVE, Suit.CLUBS))).isTrue();
            verify(mockTavolo.constructed().getFirst()).inMostra(Card.get(Rank.FIVE, Suit.CLUBS));
        }
    }

    @Test
    void testGiocaCartaTavolo() {
        try (var mockTavolo = Mockito.mockConstruction(Tavolo.class,
                (mock, context) -> when(mock.inMostra(Card.get(Rank.FIVE, Suit.CLUBS))).thenReturn(true))) {
            Partita p = new Partita();
            Giocatore g = mock(Giocatore.class);

            assertThat(p.giocaCarta(g, Card.get(Rank.FIVE, Suit.CLUBS))).isEqualTo(2);
            verify(mockTavolo.constructed().getFirst()).prendi(Card.get(Rank.FIVE, Suit.CLUBS));
            verify(g).setMazzettoTop(Rank.FIVE);
        }
    }

    @Test
    void testGiocaCartaMazzettoTop() {
        try (var ignored = Mockito.mockConstruction(Tavolo.class,
                (mock, context) -> when(mock.inMostra(any())).thenReturn(false))) {
            Partita p = new Partita();
            Giocatore g = mock(Giocatore.class);
            Giocatore g2 = mock(Giocatore.class);
            p.addGiocatore(g2);
            Card c = Card.get(Rank.FIVE, Suit.CLUBS);

            when(g2.getPunti()).thenReturn(10);
            when(g2.getMazzettoTop()).thenReturn(c.getRank());

            assertThat(p.giocaCarta(g, c)).isEqualTo(11);
            verify(g).setMazzettoTop(Rank.FIVE);
            verify(g2).rubaMazzetto();
        }
    }

    @Test
    void testGiocaCartaMessaSuTavolo() {
        try (var mockTavolo = Mockito.mockConstruction(Tavolo.class,
                (mock, context) -> when(mock.inMostra(Card.get(Rank.FIVE, Suit.CLUBS))).thenReturn(true))) {
            Partita p = new Partita();
            Giocatore g = mock(Giocatore.class);

            assertThat(p.giocaCarta(g, Card.get(Rank.NINE, Suit.CLUBS))).isEqualTo(0);
            verify(mockTavolo.constructed().getFirst()).metti(Card.get(Rank.NINE, Suit.CLUBS));
        }
    }

    @Test
    void testDistribuisciCarta(){
        try (var mockMazzo = Mockito.mockConstruction(MyDeck.class)) {
            Partita p = new Partita();
            List<Giocatore> giocatori = List.of(
                    mock(Giocatore.class),
                    mock(Giocatore.class),
                    mock(Giocatore.class)
            );
            for (Giocatore giocatore : giocatori)
                p.addGiocatore(giocatore);

            MyDeck deck = mockMazzo.constructed().getFirst();
            when(deck.draw()).thenReturn(
                    Card.get(Rank.TEN, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS));
            when(deck.size()).thenReturn(0);

            p.distribuisciCarta();
            verify(giocatori.get(0)).daiCarta(Card.get(Rank.TEN, Suit.CLUBS));
            verify(giocatori.get(1)).daiCarta(Card.get(Rank.FIVE, Suit.CLUBS));
            verify(giocatori.get(2)).daiCarta(Card.get(Rank.THREE, Suit.CLUBS));
        }
    }

    @Test
    void testDistribuisciStessoNumeroDiCarte(){
        try (var mockMazzo = Mockito.mockConstruction(MyDeck.class)) {
            Partita p = new Partita();
            List<Giocatore> giocatori = List.of(
                    mock(Giocatore.class),
                    mock(Giocatore.class),
                    mock(Giocatore.class)
            );
            for (Giocatore giocatore : giocatori)
                p.addGiocatore(giocatore);

            MyDeck deck = mockMazzo.constructed().getFirst();
            when(deck.draw()).thenReturn(
                    Card.get(Rank.TEN, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS));
            when(deck.size()).thenReturn(0);

            p.distribuisciCarta();
            verify(giocatori.get(0), times(0)).daiCarta(Card.get(Rank.TEN, Suit.CLUBS));
        }
    }
}
