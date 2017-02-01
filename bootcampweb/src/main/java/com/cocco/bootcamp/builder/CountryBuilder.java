package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 1/2/2017.
 */
public class CountryBuilder {
    private Country country;

    public CountryBuilder newCountry() {
        country = new Country();
        return this;
    }

    public CountryBuilder buildIdCountry(int idCountry) {
        country.setIdCountry(idCountry);
        return this;
    }

    public CountryBuilder buildName(String name) {
        country.setName(name);
        return this;
    }

    public CountryBuilder buildCountryID2(String countryID2) {
        country.setCountryID2(countryID2);
        return this;
    }

    public CountryBuilder buildCountryID3(String countryID3) {
        country.setCountryID3(countryID3);
        return this;
    }

    public Country getCountry() {
        return country;
    }
}
