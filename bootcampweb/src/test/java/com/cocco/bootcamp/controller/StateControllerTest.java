package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.builder.CordobaDefaultStateBuilder;
import com.cocco.bootcamp.builder.DefaultStateBuilder;
import com.cocco.bootcamp.builder.StateDirector;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.proxy.RestProxyAdapter;
import com.cocco.bootcamp.repository.CountryRepository;
import com.cocco.bootcamp.repository.StateRepository;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by santi on 3/2/2017.
 */
public class StateControllerTest extends TestCase {
    public StateControllerTest(String name) {
        super(name);
    }
    public static Test suite() {
        return new TestSuite(StateControllerTest.class);
    }

    public void testGetStatesFromCountry() throws Exception {
        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        StateRepository stateRepository = Mockito.mock(StateRepository.class);
        RestProxyAdapter restProxyAdapter = Mockito.mock(RestProxyAdapter.class);
        StateController stateController = new StateController();
        stateController.setCountryRepository(countryRepository);
        stateController.setStateRepository(stateRepository);
        stateController.setRestProxyAdapter(restProxyAdapter);

        DefaultStateBuilder defaultStateBuilder = new CordobaDefaultStateBuilder();
        StateDirector stateDirector = new StateDirector(defaultStateBuilder);
        stateDirector.constructState();
        State state = stateDirector.getState();
        List<State> states = new ArrayList<>();
        states.add(state);

        when(restProxyAdapter.getStates("ARG")).thenReturn(states);
        assertTrue(stateController.getStatesFromCountry("ARG").getBody().get(0).getName().equalsIgnoreCase("Cordoba"));
        Mockito.verifyZeroInteractions(countryRepository);
        Mockito.verifyZeroInteractions(stateRepository);
    }

    public void testAddState() throws Exception {
        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        StateRepository stateRepository = Mockito.mock(StateRepository.class);
        StateController stateController = new StateController();
        stateController.setCountryRepository(countryRepository);
        stateController.setStateRepository(stateRepository);

        DefaultStateBuilder defaultStateBuilder = new CordobaDefaultStateBuilder();
        StateDirector stateDirector = new StateDirector(defaultStateBuilder);
        stateDirector.constructState();
        State state = stateDirector.getState();

        when(countryRepository.findByCountryID3("ARG")).thenReturn(state.getCountry());
        ResponseEntity<String> re = stateController.addState(state);
        Mockito.verify(stateRepository).save(state);
        assertTrue(re.getStatusCode() == HttpStatus.CREATED);
    }
}
