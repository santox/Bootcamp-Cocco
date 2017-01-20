package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 19/1/2017.
 */
public class ArgentinaCountryBuilder implements CountryBuilder{
    private Country country;

    public ArgentinaCountryBuilder() {
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

    public void buildStates() {
        StateBuilder stateBuilder = new CordobaStateBuilder();
        StateDirector stateDirector = new StateDirector(stateBuilder);
        stateDirector.constructState(country.getCountryID3());
        State state = stateDirector.getState();
        country.getStates().add(state);
    }

    public Country getCountry() {
        return country;
    }
}
