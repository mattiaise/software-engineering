package it.unimi.di.sweng.temperature.presenter;

public class FahrenheitStrategy implements ScaleStrategy {

    private static FahrenheitStrategy instance = null;

    private FahrenheitStrategy() {
    }

    public static ScaleStrategy getInstance() {
        if (instance == null)
            return new FahrenheitStrategy();
        return instance;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * (9.0 / 5.0)) + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * (5.0 / 9.0);
    }
}
