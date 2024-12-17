package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Model extends State implements Observable<List<String>> {

    @NotNull List<Observer<List<String>>> observers = new ArrayList<>();

    @Override
    public void addObserver(@NotNull Observer<List<String>> obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update(this, getState()));
    }

    @Override
    public @NotNull List<String> getState() {
        var sorted = new ArrayList<>(getGroceryListNoAmount());
        sorted.sort(String::compareTo);
        return sorted;
    }

    @Override
    public void addToGroceryList(@NotNull String aliment, int amount) {
        super.addToGroceryList(aliment, amount);
        notifyObservers();
    }

    @Override
    public void addToGroceryPurchased(@NotNull String aliment, int amount) {
        super.addToGroceryPurchased(aliment, amount);
        notifyObservers();
    }
}
