package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 19/1/2017.
 */
public class CountryDirector {
    private CountryBuilder countryBuilder = null;

    public CountryDirector(CountryBuilder countryBuilder) {
        this.countryBuilder = countryBuilder;
    }

    public void constructCountry() {
        countryBuilder.buildName();
        countryBuilder.buildCountryID2();
        countryBuilder.buildCountryId3();
        countryBuilder.buildStates();
    }

    public Country getCountry() {
        return countryBuilder.getCountry();
    }
}
