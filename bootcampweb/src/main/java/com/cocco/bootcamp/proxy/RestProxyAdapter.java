package com.cocco.bootcamp.proxy;

import com.cocco.bootcamp.builder.CountryBuilder;
import com.cocco.bootcamp.builder.StateBuilder;
import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.domain.State;
import com.cocco.bootcamp.dto.*;
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
        RestResponseCountry restResponseCountry = jsonResponseCountry.getRestResponseCountry();
        for (WSCountryDTO wsc:restResponseCountry.getResult()) {
            countryBuilder.newCountry()
                    .buildName(wsc.getName())
                    .buildCountryID2(wsc.getAlpha2_code())
                    .buildCountryID3(wsc.getAlpha3_code());
            countries.add(countryBuilder.getCountry());
        }
        return countries;
    }

    public List<State> getStates(String country) {
        List<State> states = new ArrayList<>();
        StateBuilder stateBuilder = new StateBuilder();
        JsonResponseState jsonResponseState = countriesAndStates.getStates(country);
        RestResponseState restResponseState = jsonResponseState.getRestResponseState();
        for (WSStateDTO wss:restResponseState.getResult()) {
            stateBuilder.newState()
                    .buildCountryID3(wss.getCountry())
                    .buildName(wss.getName())
                    .buildAbbreviation(wss.getAbbr())
                    .buildArea(Long.parseLong(stripNonDigits(wss.getArea())))
                    .buildCapital(wss.getCapital());
            states.add(stateBuilder.getState());
        }
        return states;
    }
    private String stripNonDigits(final CharSequence input){
        if (input == null) {
            return "0";
        }
        final StringBuilder sb = new StringBuilder(
                input.length());
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
