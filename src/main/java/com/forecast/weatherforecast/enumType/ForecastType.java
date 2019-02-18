package com.forecast.weatherforecast.enumType;

public enum ForecastType {
    DAILY("dailyForecasts"), NIGHTLY("dailyForecasts"), PRESSURE("pressureForecasts");

    private final String forecastName;

    ForecastType(String forecastName) {
        this.forecastName = forecastName;
    }

    public String getForecastName() {
        return forecastName;
    }
}
