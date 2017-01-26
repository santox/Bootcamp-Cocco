package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.domain.Weather;
import com.cocco.bootcamp.persistence.CountryDAO;
import com.cocco.bootcamp.persistence.StateDAO;
import com.cocco.bootcamp.persistence.WeatherDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by santi on 25/1/2017.
 */
@RestController
public class WeatherController {

    //LOS PATHVARIABLES NO FUNCIONAN
    @RequestMapping(value = "/weather/{country}/{state}", method = RequestMethod.GET, headers="Accept=application/json")
    public Weather getWeather(@PathVariable("country") String country, @PathVariable("state") String state) {
        if (!CountryDAO.isAlreadyExists(country)) {
            return null;
        }
        State myState = StateDAO.getState(country, state);
        return WeatherDAO.getWeatherData(myState.getIdState());
    }

    //EL REQUEST PARAM DA EXCEPCION (CURRENT REQUEST IS NOT A MULTIPART REQUEST)
    @RequestMapping(value = "/weather", method = RequestMethod.GET, headers="Accept=application/json")
    public Weather getWeather(@RequestParam(name = "country") String country) {
        if (!CountryDAO.isAlreadyExists(country)) {
            return null;
        }
        State myState = StateDAO.getState(country, "CO");
        return WeatherDAO.getWeatherData(myState.getIdState());
    }

    //ESTO SI FUNCIONA
    @RequestMapping(value = "/country", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Country> getCountries() {
        return CountryDAO.getCountries();
    }
}
