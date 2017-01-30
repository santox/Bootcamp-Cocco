package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.*;
import com.cocco.bootcamp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by santi on 25/1/2017.
 */
@RestController
public class WeatherController {
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private WeatherRepository weatherRepository;
    private TodayWeatherRepository todayWeatherRepository;
    private WindRepository windRepository;
    private AtmosphereRepository atmosphereRepository;
    private ForecastRepository forecastRepository;

    @Autowired
    public void setWeatherRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Autowired
    public void setTodayWeatherRepository(TodayWeatherRepository todayWeatherRepository) {
        this.todayWeatherRepository = todayWeatherRepository;
    }

    @Autowired
    public void setWindRepository(WindRepository windRepository) {
        this.windRepository = windRepository;
    }

    @Autowired
    public void setAtmosphereRepository(AtmosphereRepository atmosphereRepository) {
        this.atmosphereRepository = atmosphereRepository;
    }

    @Autowired
    public void setForecastRepository(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(value = "/weather/{country}/{state}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Weather> getWeather(@PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        Country c = countryRepository.findByCountryID3(country);
        if (c == null) {
            return new ResponseEntity<Weather>(new Weather(), HttpStatus.PRECONDITION_FAILED);
        }
        State s = stateRepository.findByAbbreviation(state);
        if (s == null) {
            return new ResponseEntity<Weather>(new Weather(), HttpStatus.PRECONDITION_FAILED);
        }
        List<Weather> weatherList = weatherRepository.findByState(s);
        Weather weather = weatherList.get(weatherList.size()-1);
        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }

    @RequestMapping(value = "/weather/add/{country}/{state}", method = RequestMethod.POST)
    public ResponseEntity<String> addState(@RequestBody Weather weather, @PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        Country c = countryRepository.findByCountryID3(country);
        if (c == null) {
            return new ResponseEntity<String>(country + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        State s = stateRepository.findByAbbreviation(state);
        if (s == null) {
            return new ResponseEntity<String>(state + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        try {
            if (weather.getForecasts().size() != 10) {
                throw new Exception("You must enter 10 forecasts.");
            }
            todayWeatherRepository.save(weather.getTodayWeather());
            windRepository.save(weather.getWind());
            atmosphereRepository.save(weather.getAtmosphere());
            for (Forecast f:weather.getForecasts()) {
                forecastRepository.save(f);
            }
            weather.setState(s);
            weatherRepository.save(weather);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error message: " + e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<String>(state + " weather data added succesfully!", HttpStatus.CREATED);
    }

}
