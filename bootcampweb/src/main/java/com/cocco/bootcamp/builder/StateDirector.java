package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 19/1/2017.
 */
public class StateDirector {
    private StateBuilder stateBuilder = null;

    public StateDirector(StateBuilder stateBuilder) {
        this.stateBuilder = stateBuilder;
    }

    public void constructState(String existingCountryID3) {
        stateBuilder.buildIdState();
        stateBuilder.buildCountryID3(existingCountryID3);
        stateBuilder.buildName();
        stateBuilder.buildAbbreviation();
        stateBuilder.buildArea();
        stateBuilder.buildCapital();
        //stateBuilder.buildWeather();
    }

    public State getState() {
        return stateBuilder.getState();
    }
}
