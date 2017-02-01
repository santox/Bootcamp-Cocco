package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 19/1/2017.
 */
public class CordobaDefaultStateBuilder implements DefaultStateBuilder {
    private State state;

    public CordobaDefaultStateBuilder() {
        state = new State();
    }

    public void buildIdState() {
        state.setIdState(1);
    }

    public void buildCountryID3(String existingCountryID3) {
        state.setCountryID3(existingCountryID3);
    }

    public void buildName() {
        state.setName("Cordoba");
    }

    public void buildAbbreviation() {
        state.setAbbreviation("CO");
    }

    public void buildArea() {
        state.setArea(1263857418);
    }

    public void buildCapital() {
        state.setCapital("Cordoba");
    }

    public State getState() {
        return state;
    }
}
