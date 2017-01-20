package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.domain.Weather;

/**
 * Created by santi on 19/1/2017.
 */
public class CordobaStateBuilder implements StateBuilder{
    private State state;

    public CordobaStateBuilder() {
        state = new State();
    }

    public void buildIdState() {
        state.setIdState(1);
    }

    public void buildCountryID3(String existingCountryID3) {
        state.setCountryID3(existingCountryID3);
    }

    public void buildName() {
        state.setName("Cordoba");
    }

    public void buildAbbreviation() {
        state.setAbbreviation("CO");
    }

    public void buildArea() {
        state.setArea(1263857418);
    }

    public void buildCapital() {
        state.setCapital("Cordoba");
    }

    public void buildWeather() {
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(weatherBuilder);
        weatherDirector.constructWeather();
        Weather weather = weatherDirector.getWeather();
        state.setWeather(weather);
    }

    public State getState() {
        return state;
    }
}
