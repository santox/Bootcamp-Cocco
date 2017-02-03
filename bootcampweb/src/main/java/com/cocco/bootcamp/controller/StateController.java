package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.proxy.RestProxyAdapter;
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
    private RestProxyAdapter restProxyAdapter;

    @Autowired
    public void setRestProxyAdapter(RestProxyAdapter restProxyAdapter) {
        this.restProxyAdapter = restProxyAdapter;
    }

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
        List<State> states = new ArrayList<>();
        states = restProxyAdapter.getStates(country);
        if (!(states.size()>0)) {
            Country c = countryRepository.findByCountryID3(country);
            if (c == null) {
                return new ResponseEntity<>(states, HttpStatus.PRECONDITION_FAILED);
            }
            states = stateRepository.findByCountryID3(country);
        }
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @RequestMapping(value = "/state/add", method = RequestMethod.POST)
    public ResponseEntity<String> addState(@RequestBody State state) {
        Country c = countryRepository.findByCountryID3(state.getCountryID3());
        if (c == null) {
            return new ResponseEntity<>(state.getCountryID3() + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        if (stateRepository.findByAbbreviation(state.getAbbreviation()) != null) {
            return new ResponseEntity<>(state.getAbbreviation() + " already in use!", HttpStatus.PRECONDITION_FAILED);
        }
        if (stateRepository.findByCapital(state.getCapital()) != null) {
            return new ResponseEntity<>(state.getCapital() + " already in use!", HttpStatus.PRECONDITION_FAILED);
        }
        state.setCountry(c);
        stateRepository.save(state);
        return new ResponseEntity<>(state.getName() + " added succesfully!", HttpStatus.CREATED);
    }
}
