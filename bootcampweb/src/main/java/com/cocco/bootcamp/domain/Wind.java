package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by santi on 11/1/2017.
 */
@Entity
@Table(name = "winds")
public class Wind {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWind;
    private int windSpeed;
    private int windDirection;

    public Wind() {
        idWind = -1;
        windSpeed = -1;
        windDirection = -1;
    }

    public Wind(int idWind, int windSpeed, int windDirection) {
        this.idWind = idWind;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public int getIdWind() {
        return idWind;
    }

    public void setIdWind(int idWind) {
        this.idWind = idWind;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "\r\nSpeed=" + windSpeed +
                ", \r\nDirection=" + windDirection +
                '}';
    }
}
