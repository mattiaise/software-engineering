package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.Main;
import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.view.DisplayView;
import it.unimi.di.sweng.lab11.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisplayPresenter implements Observer<List<String>> {

    private final @NotNull Model model;
    private final @NotNull OutputView view;
    private final @NotNull GetStateStrategy<String, Integer> getModelState;

    public DisplayPresenter(@NotNull Model model, @NotNull OutputView view, @NotNull GetStateStrategy<String, Integer> getModelState) {
        this.model = model;
        this.view = view;
        this.getModelState = getModelState;
    }

    @Override
    public void update(@NotNull Observable<List<String>> subject, @NotNull List<String> state) {
        for (int i = 0; i < Main.MAX_FOOD; i++) {
            if (i < state.size()) {
                var aliment = state.get(i);
                view.set(i, "%s %d".formatted(aliment, getModelState.from().get(aliment)));
            } else view.set(i, "---");
        }
    }
}
