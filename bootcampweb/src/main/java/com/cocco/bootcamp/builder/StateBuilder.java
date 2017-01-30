package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 19/1/2017.
 */
public interface StateBuilder {
    public void buildIdState();
    public void buildCountryID3(String existingCountryID3);
    public void buildName();
    public void buildAbbreviation();
    public void buildArea();
    public void buildCapital();
    //public void buildWeather();
    public State getState();
}
