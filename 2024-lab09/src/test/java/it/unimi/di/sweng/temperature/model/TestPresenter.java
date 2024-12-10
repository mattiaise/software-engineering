package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.CelsiusStrategy;
import it.unimi.di.sweng.temperature.presenter.Presenter;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.TemperaturePresenter;
import it.unimi.di.sweng.temperature.view.MyTextView;
import it.unimi.di.sweng.temperature.view.View;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.mockito.Mockito.*;

@Timeout(2)
public class TestPresenter {

    @Test
    void testAction() {
        Model model = mock(TemperatureModel.class);
        ScaleStrategy strategy = mock(CelsiusStrategy.class);
        Presenter SUT = new TemperaturePresenter(model, strategy, mock());

        when(strategy.convertToCelsius(44.44)).thenReturn(77.77);
        SUT.action("44.44");

        verify(model).setTemp(77.77);
    }

    @Test
    void testUpdate() {
        View view = mock(MyTextView.class);
        TemperatureModel model = mock(TemperatureModel.class);
        ScaleStrategy strategy = mock(CelsiusStrategy.class);
        TemperaturePresenter SUT = new TemperaturePresenter(model, strategy, view);

        when(strategy.convertFromCelsius(44.44)).thenReturn(77.77);
        when(model.getState()).thenReturn(44.44);
        SUT.update(model, 44.44);

        verify(view).setValue("77.77");
    }

    @Test
    void testAddHandlers() {
        View view = mock(View.class);
        TemperaturePresenter SUT = new TemperaturePresenter(mock(), mock(), view);

        verify(view).addHandlers(SUT);

    }
}
