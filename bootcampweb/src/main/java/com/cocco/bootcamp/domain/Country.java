package com.cocco.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 11/1/2017.
 */
@Entity
@Table(name = "countries")
public class Country {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCountry;
    private String name;
    @Column(length = 2)
    private String countryID2;
    @Column(length = 3)
    private String countryID3;

    public Country() {
    }

    public Country(int idCountry, String name, String countryID2, String countryID3) {
        this.idCountry = idCountry;
        this.name = name;
        this.countryID2 = countryID2;
        this.countryID3 = countryID3;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryID2() {
        return countryID2;
    }

    public void setCountryID2(String countryID2) {
        this.countryID2 = countryID2;
    }

    public String getCountryID3() {
        return countryID3;
    }

    public void setCountryID3(String countryID3) {
        this.countryID3 = countryID3;
    }

    @Override
    public String toString() {
        return "Country{" +
                "\r\nidCountry=" + idCountry +
                ", \r\nname='" + name + '\'' +
                ", \r\ncountryID2='" + countryID2 + '\'' +
                ", \r\ncountryID3='" + countryID3 + '\'' +
                '}';
    }
}
