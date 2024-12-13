package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class State implements IState {

    private final @NotNull Map<String, Double> firstManche = new HashMap<>();
    private final @NotNull Map<String, Double> secondManche = new LinkedHashMap<>();
    private final @NotNull Map<String, Double> totalTime = new LinkedHashMap<>();

    @Override
    public void addFirstTimeSkier(@NotNull String name, @NotNull Double time) {
        firstManche.put(name, time);
        totalTime.put(name, time);
    }

    @Override
    public void addSecondTimeSkier(@NotNull String name, @NotNull Double time) {
        secondManche.put(name, time);
        totalTime.merge(name, time, Double::sum);
    }

    @Override
    public @NotNull Map<String, Double> getFirstManche() {
        return new HashMap<>(firstManche);
    }

    @Override
    public @NotNull Map<String, Double> getSecondManche() {
        return new HashMap<>(secondManche);
    }

    @Override
    public @NotNull Map<String, Double> getTotalTimes() {
        return new HashMap<>(totalTime);
    }
}
