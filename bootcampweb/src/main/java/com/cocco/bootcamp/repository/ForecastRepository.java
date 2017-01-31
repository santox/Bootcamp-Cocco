package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.Forecast;
import com.cocco.bootcamp.domain.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by santi on 30/1/2017.
 */
public interface ForecastRepository extends CrudRepository<Forecast, Integer> {
    public List<Forecast> findByWeather(Weather weather);
}
