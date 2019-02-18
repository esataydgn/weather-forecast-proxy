package com.forecast.weatherforecast.controller;


import com.forecast.weatherforecast.dto.WeatherAverages;
import com.forecast.weatherforecast.service.ForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/data/{cityName}")
    public ResponseEntity<WeatherAverages> retrieveData(@PathVariable String cityName) {
        WeatherAverages forecastAverages = forecastService.getForecastByCityName(cityName);
        return ResponseEntity.ok(forecastAverages);
    }

}
