package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Timeout(2)
public class TestGame {

    @ParameterizedTest
    @CsvSource(textBlock = """
      '7B,5B', -1
      '4D,RC',  0
      '7D,6B',  1
      '5D,5B',  1
      """)
    void testBeatsGame(String cards, int expected) {
        Game g = new Game(mock(Player.class), mock(Player.class));
        List<Card> cardsFrom = TestUtils.cardsFrom(cards);
        assertThat(g.beats(cardsFrom.get(0), cardsFrom.get(1))).isEqualTo(expected);
    }

    @Test
    void testPlayTurnGame() {
        Player winner = mock(Player.class);
        Player loser = mock(Player.class);
        Game g = new Game(winner, loser);

        when(winner.chooseAttackCard()).thenReturn(Card.of("5D"));
        when(loser.chooseAnswerCard(Card.of("5D"))).thenReturn(Card.of("1S"));

        g.playTurn();

        verify(winner).collectCards(Card.of("5D"), Card.of("1S"));
    }
}

