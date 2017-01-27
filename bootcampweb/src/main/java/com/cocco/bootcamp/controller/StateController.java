package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.persistence.CountryDAO;
import com.cocco.bootcamp.persistence.StateDAO;
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
    @RequestMapping(value = "/state/{country}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<State>> getStatesFromCountry(@PathVariable(value = "country") String country) {
        if (!CountryDAO.isAlreadyExists(country)) {
            return new ResponseEntity<List<State>>(new ArrayList<>(), HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<List<State>>(StateDAO.getStates(country), HttpStatus.OK);
    }

    @RequestMapping(value = "/state/add", method = RequestMethod.POST)
    public ResponseEntity<String> addState(@RequestBody State state) {
        if (!CountryDAO.isAlreadyExists(state.getCountryID3())) {
            return new ResponseEntity<String>(state.getCountryID3() + " does not exist!", HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<String>(StateDAO.addState(state.getCountryID3(), state.getName(), state.getAbbreviation(), state.getArea(), state.getCapital()), HttpStatus.CREATED);
    }
}
