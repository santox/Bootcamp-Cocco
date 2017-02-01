package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 19/1/2017.
 */
public class CountryDirector {
    private DefaultCountryBuilder defaultCountryBuilder = null;

    public CountryDirector(DefaultCountryBuilder defaultCountryBuilder) {
        this.defaultCountryBuilder = defaultCountryBuilder;
    }

    public void constructCountry() {
        defaultCountryBuilder.buildName();
        defaultCountryBuilder.buildCountryID2();
        defaultCountryBuilder.buildCountryId3();
    }

    public Country getCountry() {
        return defaultCountryBuilder.getCountry();
    }
}
