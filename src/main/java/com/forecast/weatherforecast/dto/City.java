package com.forecast.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    private Long id;
    private String name;
    private Coord coord;
    private String country;
    private String population;
}
