package com.forecast.weatherforecast.service.serviceImpl;

import com.forecast.weatherforecast.dto.WeatherAverages;
import com.forecast.weatherforecast.dto.WeatherDetails;
import com.forecast.weatherforecast.enumType.ForecastType;
import com.forecast.weatherforecast.exception.validation.CalculationException;
import com.forecast.weatherforecast.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastCalculatorService {

    private DateUtil dateUtil;

    public ForecastCalculatorService(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    public WeatherAverages calculateAvarages(List<WeatherDetails> weatherDetails) {

        WeatherAverages weatherAverages = new WeatherAverages();
        Map<ForecastType, List<WeatherDetails>> forecastListMap = findForecastTypes(weatherDetails);

        try {
            weatherAverages.setDailyAverageTemperature(formatValue(calculateDailyAverage(forecastListMap.get(ForecastType.DAILY))));
            weatherAverages.setNightlyAverageTemperature(formatValue(calculateNightlyAverage(forecastListMap.get(ForecastType.NIGHTLY))));
            weatherAverages.setPressureAverage(formatValue(calculatePressureAverage(forecastListMap.get(ForecastType.PRESSURE))));
        } catch (Exception e) {
            throw new CalculationException("An exception occur while calculation!!!");
        }
        return weatherAverages;
    }

    private Map<ForecastType, List<WeatherDetails>> findForecastTypes(List<WeatherDetails> weatherDetails) {
        List<WeatherDetails> dailyForecasts = new ArrayList<>();
        List<WeatherDetails> nightlyForecasts = new ArrayList<>();
        List<WeatherDetails> pressureForecasts = new ArrayList<>();
        for (WeatherDetails weatherDetail : weatherDetails) {
            if (dateUtil.isDateInRange(weatherDetail.getDt())) {
                if (dateUtil.isDayTimeForecast(weatherDetail.getDt())) {
                    dailyForecasts.add(weatherDetail);
                } else {
                    nightlyForecasts.add(weatherDetail);
                }
                pressureForecasts.add(weatherDetail);
            }
        }
        Map<ForecastType, List<WeatherDetails>> forecastTypeListMap = new HashMap<>();

        forecastTypeListMap.put(ForecastType.DAILY, dailyForecasts);
        forecastTypeListMap.put(ForecastType.NIGHTLY, nightlyForecasts);
        forecastTypeListMap.put(ForecastType.PRESSURE, pressureForecasts);

        return forecastTypeListMap;
    }

    private double calculateDailyAverage(List<WeatherDetails> weatherDetails) {
        return weatherDetails.stream().mapToDouble(wd -> wd.getMain().getTemp()).summaryStatistics().getAverage();
    }

    private double calculateNightlyAverage(List<WeatherDetails> weatherDetails) {
        return weatherDetails.stream().mapToDouble(wd -> wd.getMain().getTemp()).summaryStatistics().getAverage();
    }

    private double calculatePressureAverage(List<WeatherDetails> weatherDetails) {
        return weatherDetails.stream()
                .mapToDouble(detail -> detail.getMain().getPressure())
                .summaryStatistics().getAverage();
    }

    private double formatValue(double doubletoFormat) {
        return Math.round(doubletoFormat * 100) / 100D;
    }
}
