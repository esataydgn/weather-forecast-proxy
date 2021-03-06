package com.forecast.weatherforecast.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Component
public class DateUtil {
	
	private static final int FORECAST_DAYS = 3;
	private static final int DAY_START = 6;
	private static final int DAY_END = 18;
	
	public boolean isDateInRange(Instant dt) {
		LocalDateTime date = LocalDateTime.ofInstant(dt, ZoneOffset.UTC);

		Instant today = Instant.now();
		LocalDateTime maxLimit = LocalDateTime.ofInstant(today, ZoneOffset.UTC);
		maxLimit = maxLimit.plus(FORECAST_DAYS, ChronoUnit.DAYS);

		return date.getDayOfYear() <= maxLimit.getDayOfYear();
	}

	public boolean isDayTimeForecast(Instant dt) {
		LocalDateTime dtForecast = LocalDateTime.ofInstant(dt, ZoneOffset.UTC);
		return dtForecast.getHour() >= DAY_START && dtForecast.getHour() <= DAY_END;
	}
	
	
}