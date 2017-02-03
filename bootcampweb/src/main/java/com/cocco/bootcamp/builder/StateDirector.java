package com.cocco.bootcamp.builder;

import com.cocco.bootcamp.domain.State;

/**
 * Created by santi on 19/1/2017.
 */
public class StateDirector {
    private DefaultStateBuilder defaultStateBuilder = null;

    public StateDirector(DefaultStateBuilder defaultStateBuilder) {
        this.defaultStateBuilder = defaultStateBuilder;
    }

    public void constructState() {
        defaultStateBuilder.buildIdState();
        defaultStateBuilder.buildCountryID3();
        defaultStateBuilder.buildName();
        defaultStateBuilder.buildAbbreviation();
        defaultStateBuilder.buildArea();
        defaultStateBuilder.buildCapital();
        defaultStateBuilder.buildCountry();
    }

    public State getState() {
        return defaultStateBuilder.getState();
    }
}
