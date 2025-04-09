package com.tqs.backend.model;

import com.tqs.backend.entity.Meal;
import com.tqs.backend.entity.WeatherForecast;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MealWithWeatherDTOTest {

    @Test
    void testConstructorAndGetters() {
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setDescription("Arroz de pato");
        meal.setDate(LocalDate.now());

        WeatherForecast forecast = new WeatherForecast();
        forecast.setDate(LocalDate.now());
        forecast.setMaxTemp(22.5);
        forecast.setMinTemp(14.0);
        forecast.setPrecipitation(0.3);

        MealWithWeatherDTO dto = new MealWithWeatherDTO(meal, forecast);

        assertThat(dto.getDescription()).isEqualTo("Arroz de pato");
        assertThat(dto.getForecast()).isEqualTo(forecast);
    }
}
