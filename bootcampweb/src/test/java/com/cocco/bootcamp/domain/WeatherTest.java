package com.cocco.bootcamp.domain;

import com.cocco.bootcamp.builder.CordobaDefaultWeatherBuilder;
import com.cocco.bootcamp.builder.DefaultWeatherBuilder;
import com.cocco.bootcamp.builder.WeatherDirector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by santi on 20/1/2017.
 */
public class WeatherTest extends TestCase{
    private Weather weather;

    public void setUp() throws Exception {
        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(defaultWeatherBuilder);
        weatherDirector.constructWeather();
        weather = weatherDirector.getWeather();
    }

    public WeatherTest (String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( WeatherTest.class );
    }

    public void testGetIdWeather() throws Exception {
        int expectedResult = 1;
        int result = weather.getIdWeather();
        assertEquals("field wasn't retrieved properly", expectedResult, result);
    }

    public void testGetTodayWeather() throws Exception {
        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        defaultWeatherBuilder.buildTodayWeather();
        TodayWeather expectedResult = defaultWeatherBuilder.getWeather().getTodayWeather();
        TodayWeather result = weather.getTodayWeather();
        boolean isTheSame = false;
        if (result.getIdTodayWeather() == expectedResult.getIdTodayWeather()) {
            if (result.getDate().toString().equalsIgnoreCase(expectedResult.getDate().toString())) {
                if (result.getTemperature() == expectedResult.getTemperature()) {
                    if (result.getDescription().equalsIgnoreCase(expectedResult.getDescription())) {
                        isTheSame = true;
                    }
                }
            }
        }
        assertTrue("field wasn't retrieved properly", isTheSame);
    }

    public void testGetWind() throws Exception {
        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        defaultWeatherBuilder.buildWind();
        Wind expectedResult = defaultWeatherBuilder.getWeather().getWind();
        Wind result = weather.getWind();
        boolean isTheSame = false;
        if (result.getIdWind() == expectedResult.getIdWind()) {
            if (result.getWindDirection() == expectedResult.getWindDirection()) {
                if (result.getWindSpeed() == expectedResult.getWindSpeed()) {
                    isTheSame = true;
                }
            }
        }
        assertTrue("field wasn't retrieved properly", isTheSame);
    }

    public void testGetAtmosphere() throws Exception {
        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        defaultWeatherBuilder.buildAtmosphere();
        Atmosphere expectedResult = defaultWeatherBuilder.getWeather().getAtmosphere();
        Atmosphere result = weather.getAtmosphere();
        boolean isTheSame = false;
        if (result.getidAtmosphere() == expectedResult.getidAtmosphere()) {
            if (result.getHumidity() == expectedResult.getHumidity()) {
                if (result.getPressure() == expectedResult.getPressure()) {
                    if (result.getRising() == expectedResult.getRising()) {
                        if (result.getVisibility() == expectedResult.getVisibility()) {
                            isTheSame = true;
                        }
                    }
                }
            }
        }
        assertTrue("field wasn't retrieved properly", isTheSame);
    }

}
