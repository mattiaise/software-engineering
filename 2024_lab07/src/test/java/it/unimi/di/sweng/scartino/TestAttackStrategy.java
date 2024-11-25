package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Timeout(2)
public class TestAttackStrategy {

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B,2S', , 5B
      '4C,RD,1C', , 4C
      '7S,6B,2S', , 7S
      """)
    void testSceltaCasualeStrategy(String cards, Card attack, Card expected) {
       assertThat(Strategy.CARTA_CASUALE.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B,2S', , 7B
      '4C,RD,7C', , 7C
      '7D,6B,2S', , 7D
      """)
    void testSceltaRankSetteStrategy(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysRankSevenStrategy(NEXT);
        assertThat(strategy.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,6B,2S', , 7B
      '4C,RD,1C', , 7C
      '3D,6B,2S', , 7D
      """)
    void testSceltaRankSetteStrategyFail(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysRankSevenStrategy(NEXT);
        strategy.chooseCard(TestUtils.cardsFrom(cards), attack);
        verify(NEXT).chooseCard(TestUtils.cardsFrom(cards), attack);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B,2S', , 2S
      '4C,RD,7C', , RD
      '7D,6B,6S', , 7D
      """)
    void testSceltaSemeMaggioreDiBastoni(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayHigherThanBastoniSuit(NEXT);
        assertThat(strategy.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,6B,2C', , 7B
      '4C,RB,1C', , 7C
      '3C,6B,2C', , 7D
      """)
    void testSceltaSemeMaggioreDiBastoniFail(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayHigherThanBastoniSuit(NEXT);
        strategy.chooseCard(TestUtils.cardsFrom(cards), attack);
        verify(NEXT).chooseCard(TestUtils.cardsFrom(cards), attack);
    }

}
