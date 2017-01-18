package com.cocco.bootcamp.domain;

import java.util.Date;

/**
 * Created by santi on 12/1/2017.
 */
public class Forecast {
    private int idForecast;
    private Date date;
    private String day;
    private int high;
    private int low;
    private String text;

    public Forecast() {
        idForecast = -1;
        date = null;
        day = "";
        high = 9999;
        low = 9999;
        text = "";
    }

    public Forecast(int idForecast, Date date, String day, int high, int low, String text) {
        this.idForecast = idForecast;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.text = text;
    }

    public int getIdForecast() {
        return idForecast;
    }

    public void setIdForecast(int idForecast) {
        this.idForecast = idForecast;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "\r\nDate=" + date +
                ", \r\nDay='" + day + '\'' +
                ", \r\nHigh=" + high +
                ", \r\nLow=" + low +
                ", \r\nDescription='" + text + '\'' +
                '}';
    }
}
