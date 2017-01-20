package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 19/1/2017.
 */
public interface CountryBuilder {
    public void buildName();
    public void buildCountryID2();
    public void buildCountryId3();
    public void buildStates();
    public Country getCountry();
}
