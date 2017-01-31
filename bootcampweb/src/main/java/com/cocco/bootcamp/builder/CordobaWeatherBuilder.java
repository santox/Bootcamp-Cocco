package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.*;
import com.cocco.bootcamp.main.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by santi on 19/1/2017.
 */
public class CordobaWeatherBuilder implements WeatherBuilder{
    private Weather weather;

    public CordobaWeatherBuilder() {
        weather = new Weather();
    }


    public void buildIdWeather() {
        weather.setIdWeather(1);
    }

    public void buildTodayWeather() {
        TodayWeather tw = new TodayWeather();
        tw.setIdTodayWeather(1);
        tw.setDate(new Date());
        tw.setTemperature(60);
        tw.setDescription("Today weather for Cordoba.");
        weather.setTodayWeather(tw);
    }

    public void buildWind() {
        Wind w = new Wind();
        w.setIdWind(1);
        w.setWindSpeed(50);
        w.setWindDirection(40);
        weather.setWind(w);
    }

    public void buildAtmosphere() {
        Atmosphere a = new Atmosphere();
        a.setidAtmosphere(1);
        a.setHumidity(30);
        a.setPressure(20);
        a.setVisibility(10);
        a.setRising(9);
        weather.setAtmosphere(a);
    }

    public Weather getWeather() {
        return weather;
    }
}
