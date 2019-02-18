package com.forecast.weatherforecast.exception.validation;

public class InsufficientDataFromAPI extends RuntimeException {
    public InsufficientDataFromAPI(String message) {
        super(message);
    }
}
