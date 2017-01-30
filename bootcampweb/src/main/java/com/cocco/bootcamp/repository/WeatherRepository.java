package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.domain.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by santi on 30/1/2017.
 */
public interface WeatherRepository extends CrudRepository<Weather, Integer> {
    List<Weather> findByState(State state);
}
