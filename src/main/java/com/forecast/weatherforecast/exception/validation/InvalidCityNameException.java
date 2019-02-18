package com.forecast.weatherforecast.exception.validation;

public class InvalidCityNameException extends RuntimeException {

    public InvalidCityNameException(String message) {
        super(message);
    }
}
