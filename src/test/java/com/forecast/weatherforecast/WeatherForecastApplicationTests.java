package com.forecast.weatherforecast;

import com.forecast.weatherforecast.dto.WeatherAverages;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherForecastApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherForecastApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testApiStatus() {

        ResponseEntity<WeatherAverages> response = testRestTemplate.getForEntity("/data/istanbul", WeatherAverages.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getDailyAverageTemperature()).isNotNaN();
        assertThat(response.getBody().getNightlyAverageTemperature()).isNotNaN();
        assertThat(response.getBody().getPressureAverage()).isNotNaN();
    }

    @Test
    public void testCheckEmptyCityParameter() {
        ResponseEntity<WeatherAverages> response = testRestTemplate.getForEntity("/data/", WeatherAverages.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testInvalidCityName() {
        ResponseEntity<WeatherAverages> response = testRestTemplate.getForEntity("/data/istanbul123", WeatherAverages.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}