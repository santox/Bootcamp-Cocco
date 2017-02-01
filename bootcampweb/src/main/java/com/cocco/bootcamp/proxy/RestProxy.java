package com.cocco.bootcamp.proxy;

import com.cocco.bootcamp.builder.CountryBuilder;
import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.dto.RestResponseCountry;
import com.cocco.bootcamp.dto.WSCountryDTO;
import com.cocco.bootcamp.ws.CountriesAndStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
@Component
public class RestProxy {
    private CountriesAndStates countriesAndStates;

    @Autowired
    public void setCountriesAndStates(CountriesAndStates countriesAndStates) {
        this.countriesAndStates = countriesAndStates;
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        CountryBuilder countryBuilder = new CountryBuilder();
        RestResponseCountry restResponseCountry= countriesAndStates.getCountries();
        for (WSCountryDTO wsc:restResponseCountry.getResult()) {
            countryBuilder.newCountry()
                    .buildName(wsc.getName())
                    .buildCountryID2(wsc.getAlpha2_code())
                    .buildCountryID3(wsc.getAlpha3_code());
            countries.add(countryBuilder.getCountry());
        }
        return countries;
    }
}
