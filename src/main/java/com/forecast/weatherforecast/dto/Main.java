package com.forecast.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	private double temp;
	private double temp_min;
	private double temp_max;
	private double pressure;
	private double sea_level;
	private double grnd_level;
	private double humidity;
	private double temp_kf;
}
