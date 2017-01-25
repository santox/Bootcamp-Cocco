package com.cocco.bootcamp.domain;

import java.util.Date;

/**
 * Created by santi on 11/1/2017.
 */
public class TodayWeather {
    private int idTodayWeather;
    private Date date;
    private int temperature;
    private String description;

    public TodayWeather() {
        idTodayWeather = -1;
        date = null;
        temperature = 9999;
        description = "";
    }

    public TodayWeather(int idTodayWeather, Date date, int temperature, String description) {
        this.idTodayWeather = idTodayWeather;
        this.date = date;
        this.temperature = temperature;
        this.description = description;
    }

    public int getIdTodayWeather() {
        return idTodayWeather;
    }

    public void setIdTodayWeather(int idTodayWeather) {
        this.idTodayWeather = idTodayWeather;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TodayWeather{" +
                "\r\ndate=" + date +
                ", \r\ntemperature=" + temperature +
                ", \r\ndescription='" + description + '\'' +
                '}';
    }
}
