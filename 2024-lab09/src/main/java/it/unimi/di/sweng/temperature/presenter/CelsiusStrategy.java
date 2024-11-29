package it.unimi.di.sweng.temperature.presenter;

public class CelsiusStrategy implements ScaleStrategy {

    private static CelsiusStrategy instance = null;

    private CelsiusStrategy() {}

    public static CelsiusStrategy getInstance() {
        if (instance == null)
            return new CelsiusStrategy();
        return instance;
    }
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }
}
