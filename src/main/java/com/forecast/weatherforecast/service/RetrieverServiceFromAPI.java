package com.forecast.weatherforecast.service;

import com.forecast.weatherforecast.dto.WeatherAverages;

public interface RetrieverServiceFromAPI {

    WeatherAverages getForecastByCityName(String cityName);
}
