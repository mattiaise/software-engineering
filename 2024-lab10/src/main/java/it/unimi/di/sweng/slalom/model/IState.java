package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IState {

    void addFirstTimeSkier(@NotNull String name, @NotNull Double time);
    void addSecondTimeSkier(@NotNull String name, @NotNull Double time);
    @NotNull Map<String, Double> getFirstManche();
    @NotNull Map<String, Double> getSecondManche();
    @NotNull Map<String, Double> getTotalTimes();


}
