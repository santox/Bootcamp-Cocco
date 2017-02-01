package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.builder.WeatherBuilder;
import com.cocco.bootcamp.domain.*;
import com.cocco.bootcamp.dto.WeatherDTO;
import com.cocco.bootcamp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getWeather(@PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        Map<String, Object> map = new HashMap();
        Country c = countryRepository.findByCountryID3(country);
        if (c == null) {
            return new ResponseEntity<>(map, HttpStatus.PRECONDITION_FAILED);
        }
        State s = stateRepository.findByAbbreviation(state);
        if (s == null) {
            return new ResponseEntity<>(map, HttpStatus.PRECONDITION_FAILED);
        }

        List<Weather> weathers = weatherRepository.findByState(s);
        if (weathers.size() == 0) {
            return new ResponseEntity<>(map, HttpStatus.PRECONDITION_FAILED);
        }
        Weather weather = weathers.get(weathers.size()-1);
        List<Forecast> forecast = forecastRepository.findByWeather(weather);
        map.put("weather", weather);
        map.put("forecast", forecast);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/weather/add/{country}/{state}", method = RequestMethod.POST)
    public ResponseEntity<String> addWeather(@RequestBody WeatherDTO weatherDTO, @PathVariable(value = "country") String country, @PathVariable(value = "state") String state) {
        Country c = countryRepository.findByCountryID3(country);
        if (c == null) {
            return new ResponseEntity<>(country + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        State s = stateRepository.findByAbbreviation(state);
        if (s == null) {
            return new ResponseEntity<>(state + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        if (weatherDTO.getForecasts().size() != 10) {
            return new ResponseEntity<>("You must enter exactly 10 forecasts.", HttpStatus.PRECONDITION_FAILED);
        }
        weatherDTO.setState(s);
        weatherDTO.setAtmosphere(atmosphereRepository.save(weatherDTO.getAtmosphere()));
        weatherDTO.setWind(windRepository.save(weatherDTO.getWind()));
        weatherDTO.setTodayWeather(todayWeatherRepository.save(weatherDTO.getTodayWeather()));
        WeatherBuilder weatherBuilder = new WeatherBuilder();
        weatherBuilder.newWeather()
                .buildState(weatherDTO.getState())
                .buildAtmosphere(weatherDTO.getAtmosphere())
                .buildWind(weatherDTO.getWind())
                .buildTodayWeather(weatherDTO.getTodayWeather());
        Weather weather = weatherBuilder.getWeather();
        weather = weatherRepository.save(weather);
        for (Forecast f : weatherDTO.getForecasts()) {
            f.setWeather(weather);
        }
        forecastRepository.save(weatherDTO.getForecasts());
        return new ResponseEntity<>(state + " weather data added succesfully!", HttpStatus.CREATED);
    }

}
