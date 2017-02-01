package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;

/**
 * Created by santi on 19/1/2017.
 */
public interface DefaultCountryBuilder {
    public void buildName();
    public void buildCountryID2();
    public void buildCountryId3();
    public Country getCountry();
}
