package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.model.Model;
import it.unimi.di.sweng.temperature.view.View;
import org.jetbrains.annotations.NotNull;

public class TemperaturePresenter implements Observer<Double>, Presenter {

    private final @NotNull View view;
    private final @NotNull ScaleStrategy strategy;
    private final @NotNull Model model;

    public TemperaturePresenter(@NotNull Model model, @NotNull View view, @NotNull ScaleStrategy strategy) {
        this.view = view;
        this.strategy = strategy;
        this.model = model;
        view.addHandlers(this);
    }

    @Override
    public void update(@NotNull Observable<Double> subject, @NotNull Double state) {
        String value = String.format("%.2f", strategy.convertFromCelsius(subject.getState()));
        view.setValue(value);
    }

    @Override
    public void action(@NotNull String text) {
        double value = Double.parseDouble(text);
        model.setTemp(strategy.convertToCelsius(value));
    }
}
