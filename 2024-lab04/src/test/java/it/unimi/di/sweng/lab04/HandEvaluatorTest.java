package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(2)
public class HandEvaluatorTest {

    @Test
    void onePairEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.DIAMONDS),
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.SEVEN, Suit.CLUBS),
                Card.get(Rank.KING, Suit.HEARTS),
                Card.get(Rank.ACE, Suit.SPADES)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.ONE_PAIR);
    }

    @Test
    void twoPairsEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.DIAMONDS),
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.SEVEN, Suit.CLUBS),
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.ACE, Suit.SPADES)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.TWO_PAIR);
    }

    @Test
    void threeOfAKindEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.DIAMONDS),
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.KING, Suit.HEARTS),
                Card.get(Rank.ACE, Suit.SPADES)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.THREE_OF_A_KIND);
    }

    @Test
    void straightEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.DIAMONDS),
                Card.get(Rank.FIVE, Suit.CLUBS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.EIGHT, Suit.SPADES)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.STRAIGHT);
    }

    @Test
    void flushEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.KING, Suit.HEARTS),
                Card.get(Rank.JACK, Suit.HEARTS),
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.TWO, Suit.HEARTS)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.FLUSH);
    }

    @Test
    void fullHouseEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.KING, Suit.HEARTS),
                Card.get(Rank.KING, Suit.SPADES),
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.TWO, Suit.SPADES),
                Card.get(Rank.TWO, Suit.HEARTS)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.FULL_HOUSE);
    }

    @Test
    void fourOfAKindEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.KING, Suit.HEARTS),
                Card.get(Rank.KING, Suit.SPADES),
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.KING, Suit.DIAMONDS),
                Card.get(Rank.TWO, Suit.HEARTS)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.FOUR_OF_A_KIND);
    }

    @Test
    void straightFlushEvaluator() {

        List<Card> cards = List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.FIVE, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.EIGHT, Suit.HEARTS)
        );

        PokerHand pHand = new PokerHand(cards);

        assertThat(pHand.getRank()).isEqualTo(HandRank.STRAIGHT_FLUSH);
    }

}
