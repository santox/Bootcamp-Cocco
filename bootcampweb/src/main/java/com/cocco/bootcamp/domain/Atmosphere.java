package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by santi on 11/1/2017.
 */
@Entity
@Table(name = "atmospheres")
public class Atmosphere {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAtmosphere;
    private int humidity;
    private float pressure;
    private float visibility;
    private int rising;

    public Atmosphere() {
        idAtmosphere = -1;
        humidity = -1;
        pressure = -1f;
        visibility = -1f;
        rising = -1;
    }

    public Atmosphere(int idAtmosphere, int humidity, float pressure,
                      float visibility, int rising) {
        this.idAtmosphere = idAtmosphere;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.rising = rising;
    }

    public int getidAtmosphere() {
        return idAtmosphere;
    }

    public void setidAtmosphere(int idAtmosphere) {
        this.idAtmosphere = idAtmosphere;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getRising() {
        return rising;
    }

    public void setRising(int rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "\r\nHumidity=" + humidity +
                ", \r\nPressure=" + pressure +
                ", \r\nVisibility=" + visibility +
                ", \r\nRising=" + rising +
                '}';
    }
}
