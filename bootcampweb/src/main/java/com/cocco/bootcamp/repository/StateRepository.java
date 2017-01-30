package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.State;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by santi on 30/1/2017.
 */
public interface StateRepository extends CrudRepository<State, Integer> {
    List<State> findByCountryID3 (String countryID3);
    State findByAbbreviation (String abbreviation);
}
