package it.unimi.di.sweng.lab11.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IState {

    void addToGroceryList(@NotNull String aliment, int amount);
    @NotNull List<String> getGroceryListNoAmount();
}
