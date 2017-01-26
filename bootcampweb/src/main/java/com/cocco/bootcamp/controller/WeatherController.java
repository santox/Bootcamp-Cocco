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
            return null;
        }
        State myState = StateDAO.getState(country, state);
        Weather weather = WeatherDAO.getWeatherData(myState.getIdState());
        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }


    @RequestMapping(value = "/country", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Country> getCountries() {
        return CountryDAO.getCountries();
    }
}
