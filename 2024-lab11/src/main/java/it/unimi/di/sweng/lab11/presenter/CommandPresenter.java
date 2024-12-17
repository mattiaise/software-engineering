package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.Main;
import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.view.CommandView;
import it.unimi.di.sweng.lab11.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandPresenter implements Observer<List<String>>, InputPresenter {

    private final @NotNull Model model;
    private final @NotNull CommandView view;

    public CommandPresenter(@NotNull Model model, @NotNull CommandView view) {
        this.model = model;
        this.view = view;
        view.addHandlers(this);
    }

    @Override
    public void update(@NotNull Observable<List<String>> subject, @NotNull List<String> state) {
        for (int i = 0; i < Main.MAX_FOOD; i++) {
            if (i < state.size()) view.set(i, state.get(i));
            else view.set(i, "---");
        }
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        if (model.getGroceryToBuy().get(text) > 0) {
            model.addToGroceryPurchased(text, 1);
            model.addToGroceryList(text, -1);
        }
    }
}
