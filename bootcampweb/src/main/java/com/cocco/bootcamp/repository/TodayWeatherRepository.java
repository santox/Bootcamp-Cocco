package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.TodayWeather;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by santi on 30/1/2017.
 */
public interface TodayWeatherRepository extends CrudRepository<TodayWeather, Integer> {
}
