package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.MyDeck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

@Timeout(2)
public class TestMyDeck {

    private MyDeck myDeck;

    @Test
    void testIsEmptyNewDeck(){
        myDeck = new MyDeck();
        assertThat(myDeck.isEmpty()).isFalse();
    }

    @Test
    void testDrawCard(){
        try(var ignored = mockConstruction(Deck.class, (mock, context) ->
                when(mock.draw()).thenReturn(Card.get(Rank.FIVE, Suit.DIAMONDS)))) {

            myDeck = new MyDeck();
            assertThat(myDeck.draw()).isEqualTo(Card.get(Rank.FIVE, Suit.DIAMONDS));
        }
    }

    @Test
    void testSize(){
        myDeck = new MyDeck();
        assertThat(myDeck.size()).isEqualTo(52);
        while (!myDeck.isEmpty()) myDeck.draw();
        assertThat(myDeck.size()).isEqualTo(0);
    }
}
