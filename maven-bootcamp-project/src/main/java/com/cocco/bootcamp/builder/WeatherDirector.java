package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Weather;

/**
 * Created by santi on 19/1/2017.
 */
public class WeatherDirector {
    private WeatherBuilder weatherBuilder = null;

    public WeatherDirector(WeatherBuilder weatherBuilder) {
        this.weatherBuilder = weatherBuilder;
    }

    public void constructWeather() {
        weatherBuilder.buildTodayWeather();
        weatherBuilder.buildWind();
        weatherBuilder.buildAtmosphere();
        weatherBuilder.buildForecasts();
    }

    public Weather getWeather() {
        return weatherBuilder.getWeather();
    }
}
