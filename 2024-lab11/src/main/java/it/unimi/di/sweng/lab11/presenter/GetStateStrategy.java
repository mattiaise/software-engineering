package it.unimi.di.sweng.lab11.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface GetStateStrategy<T, T1> {
    @NotNull Map<T, T1> from();
}
