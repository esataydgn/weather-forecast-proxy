package com.forecast.weatherforecast.service;

import com.forecast.weatherforecast.dto.WeatherAverages;

public interface ForecastService {

    public WeatherAverages getForecastByCityName(String cityName);
}
