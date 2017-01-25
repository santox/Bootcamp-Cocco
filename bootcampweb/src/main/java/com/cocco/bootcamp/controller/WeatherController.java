package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.domain.Weather;
import com.cocco.bootcamp.persistence.CountryDAO;
import com.cocco.bootcamp.persistence.StateDAO;
import com.cocco.bootcamp.persistence.WeatherDAO;
import org.springframework.web.bind.annotation.*;

/**
 * Created by santi on 25/1/2017.
 */
@RestController
public class WeatherController {
    @RequestMapping(value = "/weather", method = RequestMethod.GET, headers="Accept=application/json")
    public Weather getWeather() {
        if (!CountryDAO.isAlreadyExists("ARG")) {
            return null;
        }
        State myState = StateDAO.getState("ARG", "CO");
        return WeatherDAO.getWeatherData(myState.getIdState());
    }
}
