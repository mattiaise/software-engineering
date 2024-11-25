package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Timeout(2)
public class TestAnswerStrategy {

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B,2S', 7S, 5B
      '4C,RD,1C', 7D, 4C
      '7S,6B,2S', 4D, 7S
      """)
    void testSceltaCasualeStrategy(String cards, Card attack, Card expected) {
        assertThat(Strategy.CARTA_CASUALE.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,RB,CS', 7S, RB
      'CC,3D,7C', 7D, CC
      '7D,6B,FS', 4D, FS
      """)
    void testSceltaFiguraStrategy(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysFigureCard(NEXT);
        assertThat(strategy.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,6B,2S', 5C, 7B
      '4C,1D,1C', 3D, 7C
      '3D,6B,2S', 4S, 7D
      """)
    void testSceltaFiguraStrategyFail(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysFigureCard(NEXT);
        strategy.chooseCard(TestUtils.cardsFrom(cards), attack);
        verify(NEXT).chooseCard(TestUtils.cardsFrom(cards), attack);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
      '5B,7B,2S', 7C, 5B
      '4C,RD,5C', FD, 5C
      '5D,6B,2S', RS, 5D
      """)
    void testSceltaRankCinqueStrategy(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysRankFiveStrategy(NEXT);
        assertThat(strategy.chooseCard(TestUtils.cardsFrom(cards), attack)).isEqualTo(expected);
    }
    @ParameterizedTest
    @CsvSource(textBlock = """
      '1B,6B,2S', 5C, 7B
      '4C,1D,1C', 3D, 7C
      '3D,6B,2S', 4S, 7D
      """)
    void testSceltaRankCinqueStrategyFail(String cards, Card attack, Card expected) {
        Strategy NEXT = mock(Strategy.class);
        Strategy strategy = new PlayAlwaysFigureCard(NEXT);
        strategy.chooseCard(TestUtils.cardsFrom(cards), attack);
        verify(NEXT).chooseCard(TestUtils.cardsFrom(cards), attack);
    }
}
