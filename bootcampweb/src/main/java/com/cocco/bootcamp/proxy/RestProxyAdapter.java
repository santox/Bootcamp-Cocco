package com.cocco.bootcamp.proxy;

import com.cocco.bootcamp.builder.CountryBuilder;
import com.cocco.bootcamp.builder.ForecastBuilder;
import com.cocco.bootcamp.builder.StateBuilder;
import com.cocco.bootcamp.builder.WeatherBuilder;
import com.cocco.bootcamp.domain.*;
import com.cocco.bootcamp.dto.*;
import com.cocco.bootcamp.weatherAdapter.*;
import com.cocco.bootcamp.ws.CountriesAndStates;
import com.cocco.bootcamp.ws.WeatherFromClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by santi on 1/2/2017.
 */
@Component
public class RestProxyAdapter {
    private CountriesAndStates countriesAndStates;
    private WeatherFromClient weatherFromClient;

    @Resource
    public void setWeatherFromClient(WeatherFromClient weatherFromClient) {
        this.weatherFromClient = weatherFromClient;
    }

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

    public Map<String, Object> getWeather(String countryID2, String stateCapital) {
        Map<String, Object> map = new HashMap();
        WeatherBuilder weatherBuilder = new WeatherBuilder();
        String url = "yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + stateCapital + ", " + countryID2 + "\")&format=json&env=store://datatables.org/alltableswithkeys";

        String q= "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + stateCapital + ", " + countryID2 + "\")";
        String format = "json";
        String env = "store://datatables.org/alltableswithkeys";
        JsonResponseWeather jsonResponseWeather = weatherFromClient.getWeather(q, format, env);
        if (jsonResponseWeather == null) {
            return null;
        }
        WeatherQuery weatherQuery = jsonResponseWeather.getQuery();
        if (weatherQuery.getResults()==null) {
            return null;
        }
        WeatherResults weatherResults = weatherQuery.getResults();
        WeatherChannel weatherChannel = weatherResults.getChannel();

        WeatherWind weatherWind = weatherChannel.getWind();
        Wind wind = new Wind();
        wind.setWindDirection(Integer.parseInt(weatherWind.getDirection()));
        wind.setWindSpeed(Integer.parseInt(weatherWind.getSpeed()));

        WeatherAtmosphere weatherAtmosphere = weatherChannel.getAtmosphere();
        Atmosphere atmosphere = new Atmosphere();
        atmosphere.setRising(Integer.parseInt(weatherAtmosphere.getRising()));
        atmosphere.setVisibility(Float.parseFloat(weatherAtmosphere.getVisibility()));
        atmosphere.setPressure(Float.parseFloat(weatherAtmosphere.getPressure()));
        atmosphere.setHumidity(Integer.parseInt(weatherAtmosphere.getHumidity()));

        WeatherItem weatherItem = weatherChannel.getItem();

        WeatherCondition weatherCondition = weatherItem.getCondition();
        TodayWeather todayWeather = new TodayWeather();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a z", Locale.US);
        try {
            todayWeather.setDate(simpleDateFormat.parse(weatherCondition.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        todayWeather.setDescription(weatherCondition.getText());
        todayWeather.setTemperature(Integer.parseInt(weatherCondition.getTemp()));

        weatherBuilder.newWeather()
                .buildWind(wind)
                .buildAtmosphere(atmosphere)
                .buildTodayWeather(todayWeather);
        Weather weather = weatherBuilder.getWeather();

        simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        ForecastBuilder forecastBuilder = new ForecastBuilder();
        List<Forecast> forecasts = new ArrayList<>();
        List<WeatherExtended> extendeds = weatherItem.getForecast();
        for (WeatherExtended we:extendeds) {
            try {
                forecastBuilder.newForecast()
                        .buildDate(simpleDateFormat.parse(we.getDate()))
                        .buildDay(we.getDay())
                        .buildHigh(Integer.parseInt(we.getHigh()))
                        .buildLow(Integer.parseInt(we.getLow()))
                        .buildText(we.getText())
                        .buildWeather(weather);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            forecasts.add(forecastBuilder.getForecast());
        }

        map.put("weather", weather);
        map.put("forecast", forecasts);
        return map;
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
