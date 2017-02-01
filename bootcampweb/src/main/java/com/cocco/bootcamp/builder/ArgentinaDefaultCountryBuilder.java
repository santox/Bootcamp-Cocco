package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 19/1/2017.
 */
public class ArgentinaDefaultCountryBuilder implements DefaultCountryBuilder {
    private Country country;

    public ArgentinaDefaultCountryBuilder() {
        country = new Country();
    }

    public void buildName() {
        country.setName("Argentina");
    }

    public void buildCountryID2() {
        country.setCountryID2("AR");
    }

    public void buildCountryId3() {
        country.setCountryID3("ARG");
    }

    public Country getCountry() {
        return country;
    }
}
