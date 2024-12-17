package it.unimi.di.sweng.lab11.view;

import it.unimi.di.sweng.lab11.presenter.InputPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class CommandView extends Region implements OutputView {

    final Label[] labels;
    final Button[] buttons;
    private final int size;

    public CommandView(@NotNull String buttonText, int numRows, @NotNull String title) {
        size = numRows;
        labels = new Label[size];
        buttons = new Button[size];

        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        Label title1 = new Label(title);
        title1.setFont(Font.font("sans", 20));
        grid.add(title1, 0, 0);
        for (int i = 0; i < size; i++) {
            labels[i] = new Label("Riga #" + i);
            labels[i].setFont(Font.font("monospace", 14));
            labels[i].setPadding(new Insets(10, 10, 10, 10));
            buttons[i] = new Button(buttonText);
            grid.add(labels[i], 0, 1 + i);
            grid.add(buttons[i], 1, 1 + i);
        }
        this.getChildren().add(grid);
    }

    public void addHandlers(@NotNull InputPresenter presenter) {
        for (int i = 0; i < size; i++) {
            final int id = i;
            buttons[i].setOnAction(eh -> presenter.action(labels[id].getText(), "1"));
        }
    }

    public void set(int i, @NotNull String s) {
        labels[i].setText(s);
    }

    @NotNull
    public String get(int i) {
        return labels[i].getText();
    }

    @Override
    public int size() {
        return size;
    }

}
