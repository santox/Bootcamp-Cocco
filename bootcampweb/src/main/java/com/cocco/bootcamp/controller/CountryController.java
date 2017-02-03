package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.proxy.RestProxyAdapter;
import com.cocco.bootcamp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CountryRepository countryRepository;
    private RestProxyAdapter restProxyAdapter;

    @Autowired
    public void setRestProxyAdapter(RestProxyAdapter restProxyAdapter) {
        this.restProxyAdapter = restProxyAdapter;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Country>> getCountries() {
        /*
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return new ResponseEntity<>(countries, HttpStatus.OK);
        */
        return new ResponseEntity<>(restProxyAdapter.getCountries(), HttpStatus.OK);
    }

    @RequestMapping(value = "/country/add", method = RequestMethod.POST)
    public ResponseEntity<String> addCountry(@RequestBody Country country) {
        if (countryRepository.findByCountryID2(country.getCountryID2()) != null) {
            return new ResponseEntity<>(country.getCountryID2() + " is already in use!", HttpStatus.PRECONDITION_FAILED);
        }
        if (countryRepository.findByCountryID3(country.getCountryID3()) != null) {
            return new ResponseEntity<>(country.getCountryID3() + " is already in use!", HttpStatus.PRECONDITION_FAILED);
        }
        countryRepository.save(country);
        return new ResponseEntity<>(country.getName() + " added succesfully!", HttpStatus.CREATED);
    }
}
