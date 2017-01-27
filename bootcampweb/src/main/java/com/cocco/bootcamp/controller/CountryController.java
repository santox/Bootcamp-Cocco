package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.persistence.CountryDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by santi on 26/1/2017.
 */
@RestController
public class CountryController {

    @RequestMapping(value = "/country", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Country> getCountries() {
        return CountryDAO.getCountries();
    }

    @RequestMapping(value = "/country/add", method = RequestMethod.POST)
    public ResponseEntity<String> addCountry(@RequestBody Country country) {
        return new ResponseEntity<String>(CountryDAO.addCountry(country.getName(), country.getCountryID2(), country.getCountryID3()), HttpStatus.CREATED);
    }
}
