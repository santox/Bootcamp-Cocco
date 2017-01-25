package com.cocco.bootcamp.domain;

import com.cocco.bootcamp.builder.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by santi on 20/1/2017.
 */
public class StateTest extends TestCase{
    private State state;

    public void setUp() throws Exception {
        StateBuilder stateBuilder = new CordobaStateBuilder();
        StateDirector stateDirector = new StateDirector(stateBuilder);
        stateDirector.constructState("ARG");
        state = stateDirector.getState();
    }

    public StateTest(String testName ) {
        super( testName );
    }
    public static Test suite() {
        return new TestSuite( StateTest.class );
    }

    public void testGetIdState() throws Exception {
        int expectedResult = 1;
        int result = state.getIdState();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetCountryID3() throws Exception {
        String expectedResult = "ARG";
        String result = state.getCountryID3();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetName() throws Exception {
        String expectedResult = "Cordoba";
        String result = state.getName();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetAbbreviation() throws Exception {
        String expectedResult = "CO";
        String result = state.getAbbreviation();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetArea() throws Exception {
        long expectedResult = 1263857418;
        long result = state.getArea();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetCapital() throws Exception {
        String expectedResult = "Cordoba";
        String result = state.getCapital();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetWeather() throws Exception {
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(weatherBuilder);
        weatherDirector.constructWeather();
        Weather expectedResult = weatherDirector.getWeather();
        Weather result = state.getWeather();
        boolean isTheSame = false;
        if (result.getIdWeather() == expectedResult.getIdWeather()) {
            if (result.getTodayWeather().getIdTodayWeather() == expectedResult.getTodayWeather().getIdTodayWeather()) {
                if (result.getWind().getIdWind() == expectedResult.getWind().getIdWind()) {
                    if (result.getAtmosphere().getidAtmosphere() == expectedResult.getAtmosphere().getidAtmosphere()) {
                        if (result.getTodayWeather().getDescription().equalsIgnoreCase(expectedResult.getTodayWeather().getDescription())) {
                            isTheSame = true;
                        }
                    }
                }
            }
        }
        assertTrue("field wasn't retrieved properly", isTheSame);
    }
}
