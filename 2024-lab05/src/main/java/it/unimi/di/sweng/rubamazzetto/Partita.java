package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Partita {

    @NotNull
    private final CountableDeck mazzo = new MyDeck();
    @NotNull
    private final List<Giocatore> giocatori = new ArrayList<>();
    private final Tavolo tavolo = new Tavolo();

    public Partita() {
        for (int i = 0; i < 4; i++) {
            tavolo.metti(mazzo.draw());
        }
    }

    public void addGiocatore(Giocatore giocatore) {
        giocatori.add(giocatore);
    }

    public void distribuisciCarta() {
        for (Giocatore giocatore : giocatori) {
            giocatore.daiCarta(mazzo.draw());
        }

        //POST CONDIZIONI
        for (Giocatore giocatore : giocatori) {
            assert giocatore.numCards() == 3 || (giocatore.numCards() < 3 && mazzo.size() < giocatori.size()) : "si possono avere meno di tre carte solo se nel mazzo non ce ne sono abbastanza per fare un altro giro";
            assert giocatori.getFirst().numCards() == giocatore.numCards() : "non è stato dato stesso numero di carte a tutti";
        }
    }

    public void distribuisciCarteIniziali() {
        for (int i = 0; i < 3; i++) {
            distribuisciCarta();
        }
    }


    public boolean isFinita() {
        assert giocatori.size() > 1;
        int cartegiocate = tavolo.size();
        for (Giocatore giocatore : giocatori) {
            cartegiocate += giocatore.getPunti();
        }
        return mazzo.size() < giocatori.size() && 52 - cartegiocate == mazzo.size();
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Giocatore giocatore : giocatori) {
            s.append(giocatore.toString());
            s.append("\n");
        }
        s.append("Tavolo: ");
        s.append(tavolo);
        s.append("\n");
        s.append("Finita: ");
        s.append(isFinita());
        return s.toString();
    }


    public boolean controllaSeCartaPresenteSuTavolo(@NotNull Card card) {
        return tavolo.inMostra(card);
    }


    public int giocaCarta(@NotNull Giocatore giocatore, @NotNull Card card) {
        if (tavolo.inMostra(card)) {
            tavolo.prendi(card);
            giocatore.setMazzettoTop(card.getRank());
            return 2;
        }
        var x = giocatori.stream()
                .filter(g -> card.getRank().equals(g.getMazzettoTop()))
                .max(Comparator.comparing(Giocatore::getPunti));
        if(x.isPresent()) {
            var g = x.get();
            giocatore.setMazzettoTop(card.getRank());
            g.rubaMazzetto();
            return g.getPunti() + 1;
        }
        tavolo.metti(card);
        return 0;
    }

}
