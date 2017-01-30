package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by santi on 11/1/2017.
 */
@Entity
@Table(name = "states")
public class State {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idState;
    @Column(length = 3)
    private String countryID3;
    private String name;
    @Column(length = 2)
    private String abbreviation;
    private long area;
    private String capital;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idCountry")
    private Country country;

    public State() {
    }

    public State(int idState, String countryID3, String name, String abbreviation, long area, String capital, Country country) {
        this.idState = idState;
        this.countryID3 = countryID3;
        this.name = name;
        this.abbreviation = abbreviation;
        this.area = area;
        this.capital = capital;
        this.country = country;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getCountryID3() {
        return countryID3;
    }

    public void setCountryID3(String countryID3) {
        this.countryID3 = countryID3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "State{" +
                "idState=" + idState +
                ", \r\ncountryID3='" + countryID3 + '\'' +
                ", \r\nname='" + name + '\'' +
                ", \r\nabbreviation='" + abbreviation + '\'' +
                ", \r\narea=" + area +
                ", \r\ncapital='" + capital + '\'' +
                ", \r\ncountry=" + country +
                '}';
    }
}
