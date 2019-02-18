package com.forecast.weatherforecast.service.serviceImpl;

import com.forecast.weatherforecast.dto.WeatherAverages;
import com.forecast.weatherforecast.dto.WeatherDetails;
import com.forecast.weatherforecast.dto.WeatherForecast;
import com.forecast.weatherforecast.exception.validation.InsufficientDataFromAPI;
import com.forecast.weatherforecast.exception.validation.InvalidCityNameException;
import com.forecast.weatherforecast.service.ForecastService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@Service
public class ForecastServiceImpl implements ForecastService {

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    @Value("${openweathermap.endPoint}")
    private String apiUrl;

    private ForecastCalculatorService forecastCalculatorService;

    public ForecastServiceImpl(ForecastCalculatorService forecastCalculatorService) {
        this.forecastCalculatorService = forecastCalculatorService;
    }

    @Override
    public WeatherAverages getForecastByCityName(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherForecast> response = null;
        WeatherForecast forecastResponse = null;
        try {

            forecastResponse = restTemplate.getForObject(createCityUrl(cityName), WeatherForecast.class);
        } catch (Exception e) {
            throw new InvalidCityNameException(cityName + " is invalid!!!");
        }

        if (Objects.isNull(forecastResponse)) {
            throw new InsufficientDataFromAPI("no data is retrieved from API!!!");
        }
        List<WeatherDetails> weatherDetails = forecastResponse.getList();
        WeatherAverages weatherAverages = forecastCalculatorService.calculateAvarages(weatherDetails);

        return weatherAverages;

    }

    private String createCityUrl(String cityName) {
        StringBuilder url = new StringBuilder();
        url.append(apiUrl).append("?q=").append(cityName).append("&mode=json&units=metric&appid=").append(apiKey);
        return url.toString();
    }
}
