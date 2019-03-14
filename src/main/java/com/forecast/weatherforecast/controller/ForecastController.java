package com.forecast.weatherforecast.controller;


import com.forecast.weatherforecast.dto.WeatherAverages;
import com.forecast.weatherforecast.service.RetrieverServiceFromAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    private final RetrieverServiceFromAPI retrieverServiceFromAPI;

    public ForecastController(RetrieverServiceFromAPI retrieverServiceFromAPI) {
        this.retrieverServiceFromAPI = retrieverServiceFromAPI;
    }

    @GetMapping("/data/{cityName}")
    public ResponseEntity<WeatherAverages> retrieveData(@PathVariable String cityName) {
        WeatherAverages forecastAverages = retrieverServiceFromAPI.getForecastByCityName(cityName);
        return ResponseEntity.ok(forecastAverages);
    }

}
