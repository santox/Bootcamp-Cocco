package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 26/1/2017.
 */
@RestController
public class CountryController {
    private CountryRepository countryRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/country/add", method = RequestMethod.POST)
    public ResponseEntity<String> addCountry(@RequestBody Country country) {
        countryRepository.save(country);
        return new ResponseEntity<String>(country.getName() + " added succesfully!", HttpStatus.CREATED);
    }
}
