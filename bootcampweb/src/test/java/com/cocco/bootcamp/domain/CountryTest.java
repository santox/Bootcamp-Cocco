package com.cocco.bootcamp.domain;

import com.cocco.bootcamp.builder.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by santi on 20/1/2017.
 */
public class CountryTest extends TestCase{
    private Country country;

    public void setUp() throws Exception {
        DefaultCountryBuilder defaultCountryBuilder = new ArgentinaDefaultCountryBuilder();
        CountryDirector countryDirector = new CountryDirector(defaultCountryBuilder);
        countryDirector.constructCountry();
        country = countryDirector.getCountry();
    }

    public CountryTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite(CountryTest.class);
    }

    public void testGetName() throws Exception {
        String expectedResult = "Argentina";
        String result = country.getName();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetCountryID2() throws Exception {
        String expectedResult = "AR";
        String result = country.getCountryID2();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetCountryID3() throws Exception {
        String expectedResult = "ARG";
        String result = country.getCountryID3();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }
    /*
    public void testGetStates() throws Exception {
        List<State> expectedResult = new ArrayList<State>();
        DefaultStateBuilder stateBuilder = new CordobaDefaultStateBuilder();
        StateDirector stateDirector = new StateDirector(stateBuilder);
        stateDirector.constructState(country.getCountryID3());
        State state = stateDirector.getState();
        expectedResult.add(state);
        List<State> result = country.getStates();
        boolean isTheSame = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getIdState() == expectedResult.get(i).getIdState()) {
                if (result.get(i).getName().equalsIgnoreCase(expectedResult.get(i).getName())) {
                    if (result.get(i).getCountryID3().equalsIgnoreCase(expectedResult.get(i).getCountryID3())) {
                        if (result.get(i).getAbbreviation().equalsIgnoreCase(expectedResult.get(i).getAbbreviation())) {
                            if (result.get(i).getArea() == expectedResult.get(i).getArea()) {
                                if (result.get(i).getCapital().equalsIgnoreCase(expectedResult.get(i).getCapital())) {
                                    isTheSame = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        assertTrue("field wasn't retrieved properly", isTheSame);
    }
    */
}
