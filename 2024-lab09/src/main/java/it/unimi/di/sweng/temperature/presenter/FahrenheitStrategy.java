package it.unimi.di.sweng.temperature.presenter;

public class FahrenheitStrategy implements ScaleStrategy {

    private static FahrenheitStrategy instance = null;

    private FahrenheitStrategy() {}

    public static FahrenheitStrategy getInstance() {
        if (instance == null)
            return new FahrenheitStrategy();
        return instance;
    }
    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * ((double) 9 /5)) + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * ((double) 5 /9);
    }
}
