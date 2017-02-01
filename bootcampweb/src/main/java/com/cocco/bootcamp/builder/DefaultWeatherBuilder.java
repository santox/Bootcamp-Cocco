package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Weather;

/**
 * Created by santi on 19/1/2017.
 */
public interface DefaultWeatherBuilder {
    public void buildIdWeather();
    public void buildTodayWeather();
    public void buildWind();
    public void buildAtmosphere();
    public Weather getWeather();
}
