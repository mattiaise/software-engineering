package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.InputView;
import org.jetbrains.annotations.NotNull;

public class NextSkierPresenter implements Presenter {

    private final @NotNull Model model;
    private final @NotNull InputView view;

    public NextSkierPresenter(@NotNull Model model, @NotNull InputView view) {
        this.model = model;
        this.view = view;
        view.addHandlers(this);
    }
    @Override
    public void action(@NotNull String text1, @NotNull String text2) {

    }
}
