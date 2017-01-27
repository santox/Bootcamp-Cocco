package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.domain.Weather;
import com.cocco.bootcamp.persistence.CountryDAO;
import com.cocco.bootcamp.persistence.StateDAO;
import com.cocco.bootcamp.persistence.WeatherDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by santi on 25/1/2017.
 */
@RestController
public class WeatherController {


    @RequestMapping(value = "/weather/{country}/{state}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Weather> getWeather(@PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        if (!CountryDAO.isAlreadyExists(country)) {
            return new ResponseEntity<Weather>(new Weather(), HttpStatus.PRECONDITION_FAILED);
        }
        State myState = StateDAO.getState(country, state);
        if (myState == null) {
            return new ResponseEntity<Weather>(new Weather(), HttpStatus.PRECONDITION_FAILED);
        }
        Weather weather = WeatherDAO.getWeatherData(myState.getIdState());
        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }

    @RequestMapping(value = "/weather/add/{country}/{state}", method = RequestMethod.POST)
    public ResponseEntity<String> addState(@RequestBody Weather weather, @PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        if (!CountryDAO.isAlreadyExists(country)) {
            return new ResponseEntity<String>(country + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        State myState = StateDAO.getState(country, state);
        if (myState == null) {
            return new ResponseEntity<String>(state + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        try {
            WeatherDAO.addWeatherItems(weather);
            WeatherDAO.addWeather(weather, myState.getIdState());
            WeatherDAO.addForecasts(weather);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error message: " + e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<String>(state + " weather added succesfully!", HttpStatus.CREATED);
    }

}
