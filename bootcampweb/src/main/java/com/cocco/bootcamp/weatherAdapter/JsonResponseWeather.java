package com.cocco.bootcamp.weatherAdapter;

/**
 * Created by santi on 3/2/2017.
 */
public class JsonResponseWeather {
    private WeatherQuery query;

    public JsonResponseWeather() {
    }

    public WeatherQuery getQuery() {
        return query;
    }

    public void setQuery(WeatherQuery query) {
        this.query = query;
    }
}
