package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTodayWeather")
    private TodayWeather todayWeather;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idWind")
    private Wind wind;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtmosphere")
    private Atmosphere atmosphere;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "weather")
    private List<Forecast> forecasts;

    @ManyToOne
    @JoinColumn(name = "idState")
    private State state;

    public Weather() {
        idWeather = -1;
        todayWeather = new TodayWeather();
        wind = new Wind();
        atmosphere = new Atmosphere();
        forecasts = new ArrayList<>();
        state = new State();
    }

    public Weather(int idWeather, TodayWeather todayWeather,
                   Wind wind, Atmosphere atmosphere, List<Forecast> forecasts, State state) {
        this.idWeather = idWeather;
        this.todayWeather = todayWeather;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.forecasts = forecasts;
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

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "\r\nidWeather=" + idWeather +
                ", \r\ntodayWeather=" + todayWeather +
                ", \r\nwind=" + wind +
                ", \r\natmosphere=" + atmosphere +
                ", \r\nforecasts=" + forecasts +
                ", \r\nstate=" + state +
                '}';
    }
}
