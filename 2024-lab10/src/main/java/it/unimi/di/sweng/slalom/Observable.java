package it.unimi.di.sweng.slalom;

import org.jetbrains.annotations.NotNull;

public interface Observable<T> {

    void addObserver(@NotNull Observer<T> obs);
    void notifyObservers();
    @NotNull T getState();
}
