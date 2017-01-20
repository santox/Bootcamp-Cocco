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

    public void buildTodayWeather() {
        weather.setTodayWeather(new TodayWeather(1, new Date(), 60, "Today weather for Cordoba."));
    }

    public void buildWind() {
        weather.setWind(new Wind(1, 50, 40));
    }

    public void buildAtmosphere() {
        weather.setAtmosphere(new Atmosphere(1, 30, 20, 10, 9));
    }

    public void buildForecasts() {
        Date sourceDate = weather.getTodayWeather().getDate();
        Date myDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        for (int i = 0; i < 10; i++) {
            myDate = DateUtil.addDays(sourceDate, i);
            String dayOfWeek = dateFormat.format(myDate);
            weather.getForecasts()[i] = new Forecast(i, myDate, dayOfWeek, 60 + i, 40 + i, "Forecast for Cordoba " + myDate);
        }
    }

    public Weather getWeather() {
        return weather;
    }
}
