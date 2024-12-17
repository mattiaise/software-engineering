package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.view.InputView;
import org.jetbrains.annotations.NotNull;

public class InputAlimentPresenter implements InputPresenter {

    private final @NotNull Model model;
    private final @NotNull InputView view;

    public InputAlimentPresenter(@NotNull Model model, @NotNull InputView view) {
        this.model = model;
        this.view = view;
        view.addHandlers(this);
    }
    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        try {
            if (!text.trim().matches("[a-zA-Z]+")) throw new IllegalArgumentException("nome alimento non valido");
            if (!text1.trim().matches("[0-9]+")) throw new IllegalArgumentException("quantità alimento non valida");
            model.addToGroceryList(text, Integer.parseInt(text1));
            model.addToGroceryPurchased(text, 0);
            view.showSuccess();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }

    }
}
