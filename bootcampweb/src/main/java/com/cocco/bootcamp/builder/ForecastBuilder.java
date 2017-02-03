package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Forecast;
import com.cocco.bootcamp.domain.Weather;

import java.util.Date;

/**
 * Created by santi on 3/2/2017.
 */
public class ForecastBuilder {
    private Forecast forecast;

    public ForecastBuilder newForecast() {
        forecast = new Forecast();
        return this;
    }

    public ForecastBuilder buildIdForecast(int idForecast) {
        forecast.setIdForecast(idForecast);
        return this;
    }

    public ForecastBuilder buildDate(Date date) {
        forecast.setDate(date);
        return this;
    }

    public ForecastBuilder buildDay(String day) {
        forecast.setDay(day);
        return this;
    }

    public ForecastBuilder buildHigh(int high) {
        forecast.setHigh(high);
        return this;
    }

    public ForecastBuilder buildLow(int low) {
        forecast.setLow(low);
        return this;
    }

    public ForecastBuilder buildText(String text) {
        forecast.setText(text);
        return this;
    }

    public ForecastBuilder buildWeather(Weather weather) {
        forecast.setWeather(weather);
        return this;
    }

    public Forecast getForecast() {
        return forecast;
    }
}
