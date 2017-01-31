package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by santi on 11/1/2017.
 */
@Entity
@Table(name = "weather")
public class Weather {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWeather;

    @ManyToOne
    @JoinColumn(name = "idTodayWeather")
    private TodayWeather todayWeather;

    @ManyToOne
    @JoinColumn(name = "idWind")
    private Wind wind;

    @ManyToOne
    @JoinColumn(name = "idAtmosphere")
    private Atmosphere atmosphere;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idState")
    private State state;

    public Weather() {

    }

    public Weather(TodayWeather todayWeather, Wind wind, Atmosphere atmosphere, State state) {
        this.idWeather = idWeather;
        this.todayWeather = todayWeather;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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


    @Override
    public String toString() {
        return "Weather{" +
                "\r\nidWeather=" + idWeather +
                ", \r\ntodayWeather=" + todayWeather +
                ", \r\nwind=" + wind +
                ", \r\natmosphere=" + atmosphere +
                ", \r\nstate=" + state +
                '}';
    }
}
