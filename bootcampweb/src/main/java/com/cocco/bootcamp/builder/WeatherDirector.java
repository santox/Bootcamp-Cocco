package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Weather;

/**
 * Created by santi on 19/1/2017.
 */
public class WeatherDirector {
    private DefaultWeatherBuilder defaultWeatherBuilder = null;

    public WeatherDirector(DefaultWeatherBuilder defaultWeatherBuilder) {
        this.defaultWeatherBuilder = defaultWeatherBuilder;
    }

    public void constructWeather() {
        defaultWeatherBuilder.buildIdWeather();
        defaultWeatherBuilder.buildTodayWeather();
        defaultWeatherBuilder.buildWind();
        defaultWeatherBuilder.buildAtmosphere();
    }

    public Weather getWeather() {
        return defaultWeatherBuilder.getWeather();
    }
}
