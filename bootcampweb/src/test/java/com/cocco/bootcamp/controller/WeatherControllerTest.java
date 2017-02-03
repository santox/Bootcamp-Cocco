package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.builder.CordobaDefaultWeatherBuilder;
import com.cocco.bootcamp.builder.DefaultWeatherBuilder;
import com.cocco.bootcamp.builder.ForecastBuilder;
import com.cocco.bootcamp.builder.WeatherDirector;
import com.cocco.bootcamp.domain.DateUtil;
import com.cocco.bootcamp.domain.Forecast;
import com.cocco.bootcamp.domain.Weather;
import com.cocco.bootcamp.dto.WeatherDTO;
import com.cocco.bootcamp.proxy.RestProxyAdapter;
import com.cocco.bootcamp.repository.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Created by santi on 3/2/2017.
 */
public class WeatherControllerTest extends TestCase {
    public WeatherControllerTest(String name) {
        super(name);
    }
    public static Test suite() {
        return new TestSuite(WeatherControllerTest.class);
    }

    public void testGetWeather() throws Exception {
        RestProxyAdapter restProxyAdapter = Mockito.mock(RestProxyAdapter.class);
        WeatherController weatherController = new WeatherController();
        weatherController.setRestProxyAdapter(restProxyAdapter);

        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(defaultWeatherBuilder);
        weatherDirector.constructWeather();
        Weather weather = weatherDirector.getWeather();

        ForecastBuilder forecastBuilder = new ForecastBuilder();
        List<Forecast> forecasts = new ArrayList<>();
        Date sourceDate = weather.getTodayWeather().getDate();
        Date myDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        for (int i = 0; i < 10; i++) {
            myDate = DateUtil.addDays(sourceDate, i);
            String dayOfWeek = dateFormat.format(myDate);
            forecastBuilder.newForecast()
                    .buildIdForecast(i)
                    .buildDate(myDate)
                    .buildDay(dayOfWeek)
                    .buildHigh(60 + i)
                    .buildLow(40 + i)
                    .buildText("Forecast for Cordoba " + myDate)
                    .buildWeather(weather);
            forecasts.add(forecastBuilder.getForecast());
        }

        Map<String, Object> map = new HashMap();
        map.put("weather", weather);
        map.put("forecast", forecasts);

        when(restProxyAdapter.getWeather("AR", "Cordoba")).thenReturn(map);
        ResponseEntity<Map<String, Object>> re = weatherController.getWeather("AR", "Cordoba");
        assertTrue(re.getBody().equals(map) && re.getStatusCode() == HttpStatus.OK);
    }

    public void testAddWeather() throws Exception {
        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        StateRepository stateRepository = Mockito.mock(StateRepository.class);
        WeatherRepository weatherRepository = Mockito.mock(WeatherRepository.class);
        TodayWeatherRepository todayWeatherRepository = Mockito.mock(TodayWeatherRepository.class);
        WindRepository windRepository = Mockito.mock(WindRepository.class);
        AtmosphereRepository atmosphereRepository = Mockito.mock(AtmosphereRepository.class);
        ForecastRepository forecastRepository = Mockito.mock(ForecastRepository.class);
        RestProxyAdapter restProxyAdapter = Mockito.mock(RestProxyAdapter.class);
        WeatherController weatherController = new WeatherController();
        weatherController.setRestProxyAdapter(restProxyAdapter);
        weatherController.setAtmosphereRepository(atmosphereRepository);
        weatherController.setCountryRepository(countryRepository);
        weatherController.setForecastRepository(forecastRepository);
        weatherController.setStateRepository(stateRepository);
        weatherController.setTodayWeatherRepository(todayWeatherRepository);
        weatherController.setWeatherRepository(weatherRepository);
        weatherController.setWindRepository(windRepository);

        DefaultWeatherBuilder defaultWeatherBuilder = new CordobaDefaultWeatherBuilder();
        WeatherDirector weatherDirector = new WeatherDirector(defaultWeatherBuilder);
        weatherDirector.constructWeather();
        Weather weather = weatherDirector.getWeather();
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setState(weather.getState());
        weatherDTO.setAtmosphere(weather.getAtmosphere());
        weatherDTO.setTodayWeather(weather.getTodayWeather());
        weatherDTO.setWind(weather.getWind());
        weatherDTO.setForecasts(new ArrayList<Forecast>());
        weatherDTO.setIdWeather(weather.getIdWeather());

        ForecastBuilder forecastBuilder = new ForecastBuilder();
        Date sourceDate = weather.getTodayWeather().getDate();
        Date myDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        for (int i = 0; i < 10; i++) {
            myDate = DateUtil.addDays(sourceDate, i);
            String dayOfWeek = dateFormat.format(myDate);
            forecastBuilder.newForecast()
                    .buildIdForecast(i)
                    .buildDate(myDate)
                    .buildDay(dayOfWeek)
                    .buildHigh(60 + i)
                    .buildLow(40 + i)
                    .buildText("Forecast for Cordoba " + myDate)
                    .buildWeather(weather);
            weatherDTO.getForecasts().add(forecastBuilder.getForecast());
        }

        when(countryRepository.findByCountryID3("ARG")).thenReturn(weather.getState().getCountry());
        when(stateRepository.findByAbbreviation("CO")).thenReturn(weather.getState());
        ResponseEntity<String> re = weatherController.addWeather(weatherDTO, "ARG", "CO");
        weather.setAtmosphere(Mockito.verify(atmosphereRepository).save(weather.getAtmosphere()));
        weather.setWind(Mockito.verify(windRepository).save(weather.getWind()));
        weather.setTodayWeather(Mockito.verify(todayWeatherRepository).save(weather.getTodayWeather()));
        for (Forecast f : weatherDTO.getForecasts()) {
            f.setWeather(weather);
        }
        Mockito.verify(forecastRepository).save(weatherDTO.getForecasts());
        assertTrue(re.getStatusCode() == HttpStatus.CREATED);
    }
}
