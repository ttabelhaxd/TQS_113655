package com.tqs.backend.model;

import com.tqs.backend.entity.Meal;
import com.tqs.backend.entity.WeatherForecast;
import com.tqs.backend.enums.MealType;

import java.time.LocalDate;

public class MealWithWeatherDTO {
    private final String description;
    private LocalDate date;
    private final MealType type;
    private final WeatherForecast forecast;

    public MealWithWeatherDTO(Meal meal, WeatherForecast forecast) {
        this.description = meal.getDescription();
        this.date = meal.getDate();
        this.type = meal.getType();
        this.forecast = forecast;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MealType getType() {
        return type;
    }

    public WeatherForecast getForecast() {
        return forecast;
    }

}
