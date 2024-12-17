package it.unimi.di.sweng.lab11;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.presenter.CommandPresenter;
import it.unimi.di.sweng.lab11.view.CommandView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Timeout(3)
public class TestPresenter {

    @Test
    void testSetDataInView() {
        Model model = mock();
        CommandView view = mock();
        CommandPresenter presenter = new CommandPresenter(model, view);

        List<String> grocery = List.of("latte", "pane", "banane");
        presenter.update(model, grocery);

        verify(view).set(0, "banane");
        verify(view).set(1, "latte");
        verify(view).set(2, "pane");
    }
}
