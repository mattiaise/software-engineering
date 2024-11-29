package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.CelsiusStrategy;
import it.unimi.di.sweng.temperature.presenter.FahrenheitStrategy;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Timeout(2)
public class TestStrategy {

    @Test
    void testCelsiusStrategy() {
        ScaleStrategy cs = CelsiusStrategy.getInstance();

        assertThat(cs.convertToCelsius(21.55)).isCloseTo(21.55, within(.01));
        assertThat(cs.convertFromCelsius(84.31)).isCloseTo(84.31, within(.01));
    }

    @Test
    void testFahrenheitStrategy() {
        ScaleStrategy fs = FahrenheitStrategy.getInstance();

        assertThat(fs.convertToCelsius(212)).isCloseTo(100, within(.01));
        assertThat(fs.convertFromCelsius(100)).isCloseTo(212, within(.01));
    }
}
