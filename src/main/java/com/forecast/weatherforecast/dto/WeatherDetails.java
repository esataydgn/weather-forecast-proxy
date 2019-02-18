package com.forecast.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {
    private Instant dt;
    private Main main;
    private Weather[] weather;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Sys sys;
    private String dt_txt;
} 
