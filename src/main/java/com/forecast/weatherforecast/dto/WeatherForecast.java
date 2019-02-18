package com.forecast.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {
    private String cod;
    private String message;
    private Long cnt;
    private List<WeatherDetails> list;
    private City city;
}

