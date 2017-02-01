package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.*;

/**
 * Created by santi on 1/2/2017.
 */
public class WeatherBuilder {
    private Weather weather = new Weather();

    public WeatherBuilder buildIdWeather(int idWeather) {
        weather.setIdWeather(idWeather);
        return this;
    }
    public WeatherBuilder buildTodayWeather(TodayWeather todayWeather) {
        weather.setTodayWeather(todayWeather);
        return this;
    }
    public WeatherBuilder buildWind(Wind wind) {
        weather.setWind(wind);
        return this;
    }
    public WeatherBuilder buildAtmosphere(Atmosphere atmosphere) {
        weather.setAtmosphere(atmosphere);
        return this;
    }
    public WeatherBuilder buildState(State state) {
        weather.setState(state);
        return this;
    }

    public Weather getWeather() {
        return weather;
    }
}
