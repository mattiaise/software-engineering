package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.CelsiusStrategy;
import it.unimi.di.sweng.temperature.presenter.FahrenheitStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Timeout(2)
public class TestStrategy {

    @Test
    void testFromCelsiusStrategy() {
        assertThat(CelsiusStrategy.getInstance().convertFromCelsius(44.44))
                .isCloseTo(44.44, within(.01));
    }

    @Test
    void testToCelsiusStrategy() {
        assertThat(CelsiusStrategy.getInstance().convertToCelsius(66.66))
                .isCloseTo(66.66, within(.01));
    }

    @Test
    void testFromFahrenheitStrategy() {
        assertThat(FahrenheitStrategy.getInstance().convertFromCelsius(100))
                .isCloseTo(212, within(.01));
    }

    @Test
    void testToFahrenheitStrategy() {
        assertThat(FahrenheitStrategy.getInstance().convertToCelsius(212))
                .isCloseTo(100, within(.01));
    }
}
