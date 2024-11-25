package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Deck;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Game {

    @NotNull
    private final Deck deck;
    @NotNull
    private final Player[] players = new Player[2];
    @NotNull
    private Player attackPlayer;

    public Game(@NotNull Player p1, @NotNull Player p2) {
        deck = Deck.createFullDeck();
        players[0] = p1;
        players[1] = p2;
        attackPlayer = p1;
    }

    @NotNull
    public Player opponentOf(@NotNull Player player) {
        if (players[0] == player)
            return players[1];
        return players[0];
    }

    public void playTurn() {
        //TODO: implementare la funzione che chiede ai giocatori
        //      le carte da giocare, applica le regole del gioco per
        //      stabilire l'eventuale vincitore della mano (usando il comparator
        //      da definire sopra) e gli consegna le 2 carte
        //      oppure le scarta

        Card attackCard = attackPlayer.chooseAttackCard();
        Card answerCard = opponentOf(attackPlayer).chooseAnswerCard(attackCard);

        if (beats(attackCard, answerCard) == 1) attackPlayer.collectCards(attackCard, answerCard);
        else if (beats(attackCard, answerCard) == -1) opponentOf(attackPlayer).collectCards(attackCard, answerCard);
    }


  public int beats(Card attack, Card answer) {
    if (attack.getRank().ordinal() > 6 || answer.getRank().ordinal() > 6) return 0;

    if (attack.getRank().equals(Rank.CINQUE) && !answer.getRank().equals(Rank.CINQUE)) return 1;
    else if (!attack.getRank().equals(Rank.CINQUE) && answer.getRank().equals(Rank.CINQUE)) return -1;

    if (attack.getSuit().ordinal() > answer.getSuit().ordinal()) return 1;
    else if (attack.getSuit().ordinal() < answer.getSuit().ordinal()) return -1;

    return (attack.getRank().ordinal() > answer.getRank().ordinal()) ?  1 : -1;
  }


  //ATTENZIONE: è private!!! decidere come gestirne il testing
    private void distribuisciCarteIniziali() {
        //TODO: implementare la funzione che distribuisce 3 carte a ciascun giocatore

    }

    public String proclamaVincitoreOPareggio() {
        //TODO: da implementare
        return "";
    }

    public @NotNull String playGame() {
        distribuisciCarteIniziali();

        while (attackPlayer.handSize() > 0) {
            playTurn();
            if (deck.remainingCards() >= 2) {
                attackPlayer.takeDrawnCard(deck.draw());
                opponentOf(attackPlayer).takeDrawnCard(deck.draw());
            }
        }
        return proclamaVincitoreOPareggio();
    }
}
