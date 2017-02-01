package com.cocco.bootcamp.proxy;

import com.cocco.bootcamp.builder.CountryBuilder;
import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.dto.JsonResponseCountry;
import com.cocco.bootcamp.dto.RestResponseCountry;
import com.cocco.bootcamp.dto.WSCountryDTO;
import com.cocco.bootcamp.ws.CountriesAndStates;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
@Component
public class RestProxyAdapter {
    private CountriesAndStates countriesAndStates;

    @Resource
    public void setCountriesAndStates(CountriesAndStates countriesAndStates) {
        this.countriesAndStates = countriesAndStates;
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        CountryBuilder countryBuilder = new CountryBuilder();
        JsonResponseCountry jsonResponseCountry = countriesAndStates.getCountries();
        RestResponseCountry restResponseCountry= jsonResponseCountry.getRestResponseCountry();
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
