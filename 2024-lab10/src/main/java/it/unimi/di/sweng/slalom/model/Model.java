package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Main;
import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.*;

public class Model extends State implements Observable<Map<String, Double>> {

    private final @NotNull List<Observer<Map<String, Double>>> observers = new ArrayList<>();

    public void readFilePrimaManche(@NotNull Scanner s) {
        while (s.hasNextLine()) {
            String linea = s.nextLine();
            String[] el = linea.split(";");
            String name = el[0];
            double time = Double.parseDouble(el[1]);
            addFirstTimeSkier(name, time);
        }
    }

    @Override
    public void addObserver(@NotNull Observer<Map<String, Double>> obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update(this, getFirstManche()));
    }

    @Override
    public @NotNull Map<String, Double> getState() {
        return getSecondManche();
    }
}
