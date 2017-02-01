package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 1/2/2017.
 */
public class StateBuilder {
    private State state;

    public StateBuilder newState() {
        state = new State();
        return this;
    }

    public StateBuilder buildIdState(int idState) {
        state.setIdState(idState);
        return this;
    }

    public StateBuilder buildCountryID3(String countryID3) {
        state.setCountryID3(countryID3);
        return this;
    }

    public StateBuilder buildName(String name) {
        state.setName(name);
        return this;
    }

    public StateBuilder buildAbbreviation(String abbreviation) {
        state.setAbbreviation(abbreviation);
        return this;
    }

    public StateBuilder buildArea(long area) {
        state.setArea(area);
        return this;
    }

    public StateBuilder buildCapital(String capital) {
        state.setCapital(capital);
        return this;
    }

    public StateBuilder buildCountry(Country country) {
        state.setCountry(country);
        return this;
    }

    public State getState() {
        return state;
    }
}
