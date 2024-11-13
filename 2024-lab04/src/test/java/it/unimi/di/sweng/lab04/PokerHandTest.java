package it.unimi.di.sweng.lab04;



import static org.assertj.core.api.Assertions.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;


@Timeout(2)
public class PokerHandTest {

  final static private List<Card> HAND_OF_FIVE =
          List.of(Card.get(Rank.KING, Suit.SPADES),
                  Card.get(Rank.FOUR, Suit.DIAMONDS),
                  Card.get(Rank.SEVEN, Suit.HEARTS),
                  Card.get(Rank.TWO, Suit.DIAMONDS),
                  Card.get(Rank.EIGHT, Suit.CLUBS));


  @Test
  void newPokerHand() {
    PokerHand pHand = new PokerHand(HAND_OF_FIVE);
    assertThat((Iterable<Card>) pHand).containsExactlyInAnyOrderElementsOf(HAND_OF_FIVE);
  }

  @Test
  void comparablePokerHand() {
    PokerHand pHand1 = new PokerHand(HAND_OF_FIVE);
    PokerHand pHand2 = new PokerHand(List.of(
            Card.get(Rank.KING, Suit.DIAMONDS),
            Card.get(Rank.KING, Suit.SPADES),
            Card.get(Rank.KING, Suit.HEARTS),
            Card.get(Rank.KING, Suit.CLUBS),
            Card.get(Rank.TWO, Suit.DIAMONDS)
    ));

    assertThat(pHand1.compareTo(pHand2)).isEqualTo(-1);
  }


}
