package it.unimi.di.sweng.lab11;

import it.unimi.di.sweng.lab11.model.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.mockito.Mockito.*;

@Timeout(2)
public class TestModel {

    @Test
    void testNotifyOnChange() {
        Model model = new Model();
        Observer<List<String>> obs = mock();

        model.addObserver(obs);
        model.addToGroceryList("latte", 5);

        verify(obs).update(model, model.getState());
    }
}
