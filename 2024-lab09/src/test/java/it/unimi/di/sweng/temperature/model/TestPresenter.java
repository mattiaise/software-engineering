package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.presenter.Presenter;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.TemperaturePresenter;
import it.unimi.di.sweng.temperature.view.View;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.mockito.Mockito.*;

@Timeout(2)
public class TestPresenter {

    @Test
    void testUpdatePresenter() {
        View view = mock();
        TemperatureModel model = mock();
        ScaleStrategy strategy = mock();
        Observer<Double> SUT = new TemperaturePresenter(model, view, strategy);

        when(strategy.convertFromCelsius(.1)).thenReturn(1324.86);
        when(model.getState()).thenReturn(.1);

        SUT.update(model, .1);
        verify(view).setValue("1324.86");
    }

    @Test
    void testAddHandlerPresenter(){
        View view = mock();
        Presenter SUT = new TemperaturePresenter(mock(), view, mock());

        verify(view).addHandlers(SUT);
    }

    @Test
    void testActionPresenter() {
        View view = mock();
        Model model = mock();
        ScaleStrategy strategy = mock();
        Presenter SUT = new TemperaturePresenter(model, view, strategy);

        when(strategy.convertToCelsius(16263.87)).thenReturn(67.1222);

        SUT.action("16263.87");

        verify(model).setTemp(67.1222);
    }
}
