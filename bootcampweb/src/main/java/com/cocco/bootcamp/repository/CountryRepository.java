package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by santi on 30/1/2017.
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findByCountryID3 (String countryID3);
    Country findByCountryID2 (String countryID2);
}
