package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Timeout(2)
public class PokerTableTest {

    private static Deck createEmptyDeck() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) deck.draw();
        return deck;
    }

    private static Deck createDeckWithCards() {
        Deck deck = createEmptyDeck();
        for (Card card : HAND_OF_FIVE) deck.push(card);
        return deck;
    }

    final static private List<Card> HAND_OF_FIVE =
            List.of(Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.FOUR, Suit.DIAMONDS),
                    Card.get(Rank.SEVEN, Suit.HEARTS),
                    Card.get(Rank.TWO, Suit.DIAMONDS),
                    Card.get(Rank.EIGHT, Suit.CLUBS));

    @Test
    void newPokerTable() throws NoSuchFieldException, IllegalAccessException {
        Deck deck = createDeckWithCards();
        PokerTable pTable = new PokerTable(deck, 1);

        Field field = PokerTable.class.getDeclaredField("players");
        field.setAccessible(true);
        List<PokerHand> lstHand = (List<PokerHand>) field.get(pTable);

        assertThat((Iterable<Card>) lstHand.get(0)).containsExactlyInAnyOrderElementsOf(HAND_OF_FIVE);
    }

    @Test
    void pokerTableIterator() {
        Deck deck = createDeckWithCards();
        PokerTable pTable = new PokerTable(deck, 1);

        for (PokerHand pHand : pTable)
            assertThat((Iterable<Card>) pHand).containsExactlyInAnyOrderElementsOf(HAND_OF_FIVE);
    }

    @Test
    void pokerTableGetHand() {
        Deck deck = createDeckWithCards();
        PokerTable pTable = new PokerTable(deck, 1);

        assertThat((Iterable<Card>) pTable.getHand(0)).containsExactlyInAnyOrderElementsOf(HAND_OF_FIVE);
    }

    @Test
    void pokerTableChange() {
        Deck deck = createDeckWithCards();
        PokerTable pTable = new PokerTable(deck, 1);

        List<Card> toChange = List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.NINE, Suit.CLUBS),
                Card.get(Rank.SIX, Suit.DIAMONDS)
        );

        pTable.change(0, toChange);
        assertThat((Iterable<Card>) pTable.getHand(0)).containsAll(toChange);
        assertThat(pTable.getHand(0).size()).isEqualTo(5);
    }
}
