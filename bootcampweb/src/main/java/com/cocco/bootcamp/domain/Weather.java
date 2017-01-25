package com.cocco.bootcamp.domain;

import java.util.Arrays;

/**
 * Created by santi on 11/1/2017.
 */
public class Weather {
    private int idWeather;
    private TodayWeather todayWeather;
    private Wind wind;
    private Atmosphere atmosphere;
    private Forecast[] forecasts;

    public Weather() {
        idWeather = -1;
        todayWeather = new TodayWeather();
        wind = new Wind();
        atmosphere = new Atmosphere();
        forecasts = new Forecast[10];
    }

    public Weather(int idWeather, TodayWeather todayWeather,
                   Wind wind, Atmosphere atmosphere, Forecast[] forecasts) {
        this.idWeather = idWeather;
        this.todayWeather = todayWeather;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.forecasts = forecasts;
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(int idWeather) {
        this.idWeather = idWeather;
    }

    public TodayWeather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(TodayWeather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Forecast[] getForecasts() {
        return forecasts;
    }

    public void setForecasts(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "\r\nToday=" + todayWeather +
                ", \r\nWind=" + wind +
                ", \r\nAtmosphere=" + atmosphere +
                ", \r\nForecasts=" + Arrays.toString(forecasts) +
                '}';
    }
}
