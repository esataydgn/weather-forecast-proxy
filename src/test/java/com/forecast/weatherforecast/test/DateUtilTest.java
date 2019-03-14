package com.forecast.weatherforecast.test;

import com.forecast.weatherforecast.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {

    private DateUtil dateUtil;

    @Before
    public void setup() {
        dateUtil = new DateUtil();
    }
    @Test
    public void testIsDateInRange() {
        LocalDateTime expectedMaxLimit = LocalDateTime.now().plusDays(2);
        assertThat(dateUtil.isDateInRange(expectedMaxLimit.toInstant(ZoneOffset.UTC))).isEqualTo(true);
    }

    @Test
    public void testIsDayTimeForecast() {
        LocalDateTime dateTime = LocalDateTime.of(2019, Month.FEBRUARY, 13, 15, 00);
        Instant instant = dateTime.atZone(ZoneId.of("Europe/Paris")).toInstant();
        assertThat(dateUtil.isDayTimeForecast(instant));
    }

    @Test
    public void testIsNightTimeForecast() {
        LocalDateTime dateTime = LocalDateTime.of(2019, Month.FEBRUARY, 20, 15, 00);
        Instant instant = dateTime.atZone(ZoneId.of("Europe/Paris")).toInstant();
        assertThat(!dateUtil.isDayTimeForecast(instant));
    }
}