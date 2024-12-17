package it.unimi.di.sweng.lab11;

import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
    void addObserver(@NotNull Observer<T> obs);
    void notifyObservers();
    @NotNull T getState();
}
