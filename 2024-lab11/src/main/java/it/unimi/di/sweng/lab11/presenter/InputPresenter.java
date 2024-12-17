package it.unimi.di.sweng.lab11.presenter;

import org.jetbrains.annotations.NotNull;

public interface InputPresenter {
  // nel caso della InputAlimentView viene invocato con alimento e quantità
  // nel caso delle CommandView viene invocato con il testo dell'alimento acquistato e la costante "1" (che indica il numero di item comprati)
  void action(@NotNull String text, @NotNull String text1);
}
