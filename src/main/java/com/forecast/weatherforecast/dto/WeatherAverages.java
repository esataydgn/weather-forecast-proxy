package com.forecast.weatherforecast.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WeatherAverages {
	private double dailyAverageTemperature;
	private double nightlyAverageTemperature;
	private double pressureAverage;
}
