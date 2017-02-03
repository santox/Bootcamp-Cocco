package com.cocco.bootcamp.controller;

import com.cocco.bootcamp.builder.ArgentinaDefaultCountryBuilder;
import com.cocco.bootcamp.builder.CountryDirector;
import com.cocco.bootcamp.builder.DefaultCountryBuilder;
import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.proxy.RestProxyAdapter;
import com.cocco.bootcamp.repository.CountryRepository;
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
public class CountryControllerTest extends TestCase {

    public CountryControllerTest(String name) {
        super(name);
    }
    public static Test suite() {
        return new TestSuite(CountryControllerTest.class);
    }

    public void testGetCountries() throws Exception {
        CountryController countryController = new CountryController();
        RestProxyAdapter restProxyAdapter = Mockito.mock(RestProxyAdapter.class);
        countryController.setRestProxyAdapter(restProxyAdapter);

        List<Country> countries = new ArrayList<>();
        DefaultCountryBuilder defaultCountryBuilder = new ArgentinaDefaultCountryBuilder();
        CountryDirector countryDirector = new CountryDirector(defaultCountryBuilder);
        countryDirector.constructCountry();
        Country country = countryDirector.getCountry();
        countries.add(country);

        when(restProxyAdapter.getCountries()).thenReturn(countries);
        ResponseEntity<List<Country>> responseEntity = countryController.getCountries();
        Mockito.verify(restProxyAdapter).getCountries();
        assertTrue(responseEntity.getBody().get(0).getName().equalsIgnoreCase("Argentina") && responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
    }

    public void testAddCountry() throws Exception {
        CountryController countryController = new CountryController();
        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        countryController.setCountryRepository(countryRepository);

        DefaultCountryBuilder defaultCountryBuilder = new ArgentinaDefaultCountryBuilder();
        CountryDirector countryDirector = new CountryDirector(defaultCountryBuilder);
        countryDirector.constructCountry();
        Country country = countryDirector.getCountry();

        countryController.addCountry(country);
        Mockito.verify(countryRepository).save(country);
    }
}
