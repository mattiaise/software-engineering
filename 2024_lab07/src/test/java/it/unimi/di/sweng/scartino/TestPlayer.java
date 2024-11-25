package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(2)
public class TestPlayer {

    private Player p;

    @BeforeEach
    void setUp() {
        p = new Player("Carlo");
    }

    @Test
    void testIterablePlayer() {
        p.takeDrawnCard(Card.of("2B"));
        p.takeDrawnCard(Card.of("4C"));

        List<Card> cards = TestUtils.cardsFrom("2B, 4C");

        assertThat(p).containsExactlyInAnyOrderElementsOf(cards);
    }

    @Test
    void testHandSizePlayer() {
        p.takeDrawnCard(Card.of("2B"));
        p.takeDrawnCard(Card.of("4C"));

        assertThat(p.handSize()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B', 12
      '2B,7B', 9
      '2B,3B,FS,RB', 5
      """)
    void testGetPointsPlayer(String cards, int points) {
        List<Card> cardsFrom = TestUtils.cardsFrom(cards);
        for (int i = 0; i+1 < cardsFrom.size(); i+=2)
            p.collectCards(cardsFrom.get(i), cardsFrom.get(i+1));
        assertThat(p.getPoints()).isEqualTo(points);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '1B,5B,2S', 5C, 5B
      'RC,1D,1C', 3D, RC
      '3D,6B,2S', 4S, 3D
      """)
    void testChooseAnswerCardPlayer(String cards, Card attack, Card expected) {
        for (Card card : TestUtils.cardsFrom(cards)) p.takeDrawnCard(card);
        p.setAnswerStrategyChain(new PlayAlwaysRankFiveStrategy(new PlayAlwaysFigureCard(Strategy.CARTA_CASUALE)));
        assertThat(p.chooseAnswerCard(attack)).isEqualTo(expected);
    }

}
