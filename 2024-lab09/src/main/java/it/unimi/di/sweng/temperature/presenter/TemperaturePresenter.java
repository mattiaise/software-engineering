package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.model.Model;
import it.unimi.di.sweng.temperature.view.View;
import org.jetbrains.annotations.NotNull;

public class TemperaturePresenter implements Presenter, Observer<Double> {

    private final @NotNull Model model;
    private final @NotNull ScaleStrategy strategy;
    private final @NotNull View view;

    public TemperaturePresenter(@NotNull Model model, @NotNull ScaleStrategy strategy, @NotNull View view) {
        this.model = model;
        this.strategy = strategy;
        this.view = view;
        view.addHandlers(this);
    }

    @Override
    public void update(@NotNull Observable<Double> subject, @NotNull Double state) {
        view.setValue("%.2f".formatted(strategy.convertFromCelsius(state)));
    }

    @Override
    public void action(@NotNull String text) {
        model.setTemp(strategy.convertToCelsius(Double.parseDouble(text)));
    }
}
