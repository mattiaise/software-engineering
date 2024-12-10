package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.model.TemperatureModel;
import org.jetbrains.annotations.NotNull;

public interface Presenter {
  void action(@NotNull String text);
}
