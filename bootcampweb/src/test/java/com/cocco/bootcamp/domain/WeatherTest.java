package com.cocco.bootcamp.domain;

import com.cocco.bootcamp.builder.CordobaWeatherBuilder;
import com.cocco.bootcamp.builder.WeatherBuilder;
import com.cocco.bootcamp.builder.WeatherDirector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Created by santi on 20/1/2017.
 */
public class WeatherTest extends TestCase{
    private Weather weather;

    public void setUp() throws Exception {
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(weatherBuilder);
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
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        weatherBuilder.buildTodayWeather();
        TodayWeather expectedResult = weatherBuilder.getWeather().getTodayWeather();
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
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        weatherBuilder.buildWind();
        Wind expectedResult = weatherBuilder.getWeather().getWind();
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
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        weatherBuilder.buildAtmosphere();
        Atmosphere expectedResult = weatherBuilder.getWeather().getAtmosphere();
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

    public void testGetForecasts() throws Exception {
        WeatherBuilder weatherBuilder = new CordobaWeatherBuilder();
        weatherBuilder.buildTodayWeather();
        weatherBuilder.buildForecasts();
        List<Forecast> expectedResult = weatherBuilder.getWeather().getForecasts();
        List<Forecast> result = weather.getForecasts();
        boolean isTheSame = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getIdForecast() == expectedResult.get(i).getIdForecast()) {
                if (result.get(i).getDate().toString().equalsIgnoreCase(expectedResult.get(i).getDate().toString())) {
                    if (result.get(i).getDay().equalsIgnoreCase(expectedResult.get(i).getDay())) {
                        if (result.get(i).getHigh() == expectedResult.get(i).getHigh()) {
                            if (result.get(i).getLow() == expectedResult.get(i).getLow()) {
                                if (result.get(i).getText().equalsIgnoreCase(expectedResult.get(i).getText())) {
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
}
