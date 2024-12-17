package it.unimi.di.sweng.lab11.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State implements IState {

    private final @NotNull Map<String, Integer> toBuy = new HashMap<>();
    private final @NotNull Map<String, Integer> purchased = new HashMap<>();

    @Override
    public void addToGroceryList(@NotNull String aliment, int amount) {
        toBuy.merge(aliment, amount, Integer::sum);
    }

    public void addToGroceryPurchased(@NotNull String aliment, int amount) {
        purchased.merge(aliment, amount, Integer::sum);
    }

    @Override
    public @NotNull List<String> getGroceryListNoAmount() {
        // .toList() returns ad unmodifiable list, NO REFERENCE ESCAPING
        return toBuy.keySet().stream().toList();
    }

    public @NotNull Map<String, Integer> getGroceryToBuy() {
        return new HashMap<>(toBuy);
    }
    public @NotNull Map<String, Integer> getGroceryPurchased() {
        return new HashMap<>(purchased);
    }
}
