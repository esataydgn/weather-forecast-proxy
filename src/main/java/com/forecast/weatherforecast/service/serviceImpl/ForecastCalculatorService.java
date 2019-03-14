package com.forecast.weatherforecast.service.serviceImpl;

import com.forecast.weatherforecast.dto.WeatherAverages;
import com.forecast.weatherforecast.dto.WeatherDetails;
import com.forecast.weatherforecast.exception.validation.CalculationException;
import com.forecast.weatherforecast.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.ToDoubleFunction;

@Service
public class ForecastCalculatorService {

    private DateUtil dateUtil;

    public ForecastCalculatorService(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    public WeatherAverages calculateAvarages(List<WeatherDetails> weatherDetails) {

        try {
            return findForecastTypes(weatherDetails);
        } catch (Exception e) {
            throw new CalculationException("An exception occur while calculation!!!");
        }

    }

    private WeatherAverages findForecastTypes(List<WeatherDetails> weatherDetails) {
        WeatherAverages weatherAverages = new WeatherAverages();
        Double dailyAvarage = 0.00;
        Double nightlyAvarage = 0.00;
        Double pressureAvarage = 0.00;
        int dailyCount = 0;
        int totalCount = 0;


        for (WeatherDetails weatherDetail : weatherDetails) {
            if (dateUtil.isDateInRange(weatherDetail.getDt())) {
                if (dateUtil.isDayTimeForecast(weatherDetail.getDt())) {
                    dailyAvarage += weatherDetail.getMain().getTemp();
                    dailyCount++;
                } else {
                    nightlyAvarage += weatherDetail.getMain().getTemp();
                }
                pressureAvarage += weatherDetail.getMain().getTemp();
                totalCount++;
            }
        }

        weatherAverages.setDailyAverageTemperature(dailyAvarage / dailyCount);
        weatherAverages.setNightlyAverageTemperature(nightlyAvarage / (totalCount - dailyCount));
        weatherAverages.setPressureAverage(pressureAvarage / totalCount);

        return weatherAverages;
    }

    private double calculateAverage(List<WeatherDetails> weatherDetails, ToDoubleFunction<WeatherDetails> getDouble) {
        return weatherDetails.stream().mapToDouble(getDouble).summaryStatistics().getAverage();
    }


    private double formatValue(double doubletoFormat) {
        return Math.round(doubletoFormat * 100) / 100D;
    }
}
