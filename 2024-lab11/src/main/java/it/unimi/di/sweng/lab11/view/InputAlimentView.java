package it.unimi.di.sweng.lab11.view;

import it.unimi.di.sweng.lab11.presenter.InputPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class InputAlimentView extends Region implements InputView {
    final TextField text = new TextField();
    final TextField num = new TextField();
    final Button addButton = new Button("Add");

    @NotNull
    private final Label error;


    public InputAlimentView() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        error = new Label("");
        num.setPrefColumnCount(2);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(text, 0, 0);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(num, 1, 0);
        grid.add(addButton, 2, 0);

        this.getChildren().add(grid);
    }

    public void addHandlers(@NotNull InputPresenter presenter) {
        addButton.setOnAction(eh -> presenter.action(text.getText(), num.getText()));
    }

    @Override
    public void showError(@NotNull String s) {
        error.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        error.setText("");
        text.clear();
        num.clear();
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    }
}
