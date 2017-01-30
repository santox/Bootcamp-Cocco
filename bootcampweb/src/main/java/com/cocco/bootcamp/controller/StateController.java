package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.repository.CountryRepository;
import com.cocco.bootcamp.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 26/1/2017.
 */
@RestController
public class StateController {
    private CountryRepository countryRepository;
    private StateRepository stateRepository;

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(value = "/state/{country}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<State>> getStatesFromCountry(@PathVariable(value = "country") String country) {
        Country c = countryRepository.findByCountryID3(country);
        if (c == null) {
            return new ResponseEntity<List<State>>(new ArrayList<>(), HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<List<State>>(stateRepository.findByCountryID3(country), HttpStatus.OK);
    }

    @RequestMapping(value = "/state/add", method = RequestMethod.POST)
    public ResponseEntity<String> addState(@RequestBody State state) {
        Country c = countryRepository.findByCountryID3(state.getCountryID3());
        if (c == null) {
            return new ResponseEntity<String>(state.getCountryID3() + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        state.setCountry(c);
        stateRepository.save(state);
        return new ResponseEntity<String>(state.getName() + " added succesfully!", HttpStatus.CREATED);
    }
}
