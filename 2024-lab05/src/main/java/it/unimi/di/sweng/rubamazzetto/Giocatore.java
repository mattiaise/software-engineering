package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Giocatore implements Iterable<Card> {

  private final String nome;
  private final List<Card> mano = new ArrayList<>();
  private @Nullable Rank mazzettoTop;
  private final Partita partita;
  private final Strategy strategy = Strategy.FIRST_CARD;

  private int punti;

  public Giocatore(String nome, Partita partita) {
    this.nome = nome;
    partita.addGiocatore(this);
    this.partita = partita;
  }

  public void setMazzettoTop(@Nullable Rank rank) {
    mazzettoTop = rank;
  }

  public @Nullable Rank getMazzettoTop() {
    return mazzettoTop;
  }

  public int getPunti() {
    return punti;
  }

  public void daiCarta(Card carta) {
    mano.add(carta);
  }


  public void turno() {
    Card card = strategy.chooseCard(partita, this);
    punti += partita.giocaCarta(this, card);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(nome);
    s.append(": ");
    s.append("[").append(mano.size()).append("]");
    if (punti > 0) {
      s.append("mazzetto con ");
      s.append(punti);
      s.append(" carte, cima ");
      s.append(mazzettoTop);
      s.append("; ");
    }
    for (Card card : mano) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }

  public int numCards() {
    return mano.size();
  }

  @Override
  public @NotNull Iterator<Card> iterator() {
    return mano.iterator();
  }

  public void rubaMazzetto() {
    mazzettoTop = null;
    punti = 0;
  }
}
