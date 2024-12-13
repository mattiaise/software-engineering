package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Main;
import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.views.OutputView;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DisplayPresenter implements Observer<Map<String, Double>> {

    private final @NotNull Model model;
    private final @NotNull OutputView view;

    public DisplayPresenter(@NotNull Model model, @NotNull OutputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(@NotNull Observable<Map<String, Double>> subject, @NotNull Map<String, Double> state) {
        var sorted = state.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (int i = 0; i < Main.SKIER_NUM; i++)
            view.set(i, "%-27s %6.2f".formatted(
                    sorted.entrySet().stream().toList().get(i).getKey(),
                    sorted.entrySet().stream().toList().get(i).getValue()));
    }
}

