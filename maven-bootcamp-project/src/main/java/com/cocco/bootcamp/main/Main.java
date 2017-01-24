package com.cocco.bootcamp.main;

import com.cocco.bootcamp.config.*;
import com.cocco.bootcamp.domain.*;
import com.cocco.bootcamp.persistence.CountryController;
import com.cocco.bootcamp.persistence.StateController;
import com.cocco.bootcamp.persistence.WeatherController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int option = 0;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        List<Country> countries = new ArrayList<Country>();

        //DB CONNECTION TEST
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
        DataSource dc = (DataSource) applicationContext.getBean("dataSource");
        try {
            dc.openConnection();
        } catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }finally {
            dc.closeConnection();
        }

        System.out.println("Welcome to Santiago Cocco's app for Globant's Java Bootcamp 2017!");
        System.out.println("Contact email: santi-cocco@hotmail.com");

        while (!exit){
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1 - Add a country.");
            System.out.println("2 - Show countries.");
            System.out.println("3 - Add a state to an existing country.");
            System.out.println("4 - Show states from an existing country.");
            System.out.println("5 - Add new weather data to an existing state.");
            System.out.println("6 - Show the latest weather data from an existing state.");
            System.out.println("7 - Exit.");
            System.out.println("Write the number of the desired action.");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    //Country input
                    System.out.println();
                    System.out.println("Country name: ");
                    String name = scanner.next();
                    System.out.println();
                    System.out.println(name + " 2 letter abbreviation: ");
                    String countryID2 = scanner.next();
                    System.out.println();
                    System.out.println(name + " 3 letter abbreviation: ");
                    String countryID3 = scanner.next();

                    /*
                    Country country = new Country();
                    country.setName(name);
                    country.setCountryID2(countryID2);
                    country.setCountryID3(countryID3);
                    countries.add(country);
                    */

                    System.out.println();
                    System.out.println(CountryController.addCountry(name, countryID2, countryID3));
                    break;
                case 2:
                    //Fetch countries from DataBase
                    countries = CountryController.getCountries();

                    //Displaying countries
                    System.out.println();
                    StringBuilder sb = new StringBuilder();
                    for (Country c : countries){
                        sb.append(c.toString());
                        sb.append("\r\n");
                    }
                    System.out.println(sb.toString());
                    break;
                case 3:
                    //Selected country input
                    System.out.println();
                    System.out.println("3 letter country abbreviation: ");
                    String abbr = scanner.next();
                    if (!CountryController.isAlreadyExists(abbr)) {
                        System.out.println("Country does not exist.");
                        break;
                    }

                    /*Country selectedCountry = null;
                    for(Country co : countries){
                        if (co.getCountryID3().equalsIgnoreCase(abbr)){
                            selectedCountry = co;
                        }
                    }
                    if (selectedCountry == null) {break;}*/

                    //State input
                    System.out.println();
                    System.out.println("Name of the state to be added to " + abbr + ": ");
                    String stateName = scanner.next();
                    System.out.println();
                    System.out.println(stateName + "2 letter abbreviation: ");
                    String stateAbbreviation = scanner.next();
                    System.out.println();
                    System.out.println("Size of the area (in KM): ");
                    long area = scanner.nextLong();
                    System.out.println();
                    System.out.println(stateName + " capital: ");
                    String capital = scanner.next();

                    /*State state = new State();
                    state.setCountryID3(selectedCountry.getCountryID3());
                    state.setName(stateName);
                    state.setAbbreviation(stateAbbreviation);
                    state.setArea(area);
                    state.setCapital(capital);
                    selectedCountry.addState(state);*/

                    System.out.println();
                    System.out.println(StateController.addState(abbr, stateName, stateAbbreviation, area, capital));
                    break;
                case 4:
                    //Selected country input
                    System.out.println();
                    System.out.println("3 letter country abbreviation: ");
                    String countryAbbr = scanner.next();
                    if (!CountryController.isAlreadyExists(countryAbbr)) {
                        System.out.println("Country does not exist.");
                        break;
                    }

                    /*Country selecCountry = null;
                    for(Country cou : countries){
                        if (cou.getCountryID3().equalsIgnoreCase(countryAbbr)){
                            selecCountry = cou;
                        }
                    }
                    if (selecCountry == null) {break;}*/

                    List<State> stateList = StateController.getStates(countryAbbr);
                    //States output
                    System.out.println();
                    StringBuilder builder = new StringBuilder();
                    for (State s : stateList){
                        builder.append(s.toString());
                        builder.append("\r\n");
                    }
                    System.out.println(builder.toString());
                    break;
                case 5:
                    //Selected country input
                    System.out.println();
                    System.out.println("3 letter country abbreviation: ");
                    String cAbbr = scanner.next();
                    if (!CountryController.isAlreadyExists(cAbbr)) {
                        System.out.println("Country does not exist.");
                        break;
                    }

                    /*Country selCountry = null;
                    for(Country co : countries){
                        if (co.getCountryID3().equalsIgnoreCase(cAbbr)){
                            selCountry = co;
                        }
                    }
                    if (selCountry == null) {break;}*/

                    //Selected state input
                    System.out.println();
                    System.out.println("2 letter state abbreviation: ");
                    String stateAbbr = scanner.next();
                    State selectedState = StateController.getState(cAbbr, stateAbbr);
                    if (selectedState == null) {
                        System.out.println("State does not exist.");
                        break;
                    }


                    /*State selectedState = null;
                    for (State st : selCountry.getStates()){
                        if (st.getAbbreviation().equalsIgnoreCase(stateAbbr)){
                            selectedState = st;
                        }
                    }
                    if (selectedState == null) {break;}*/

                    Weather weather = new Weather();

                    //Today weather input
                    System.out.println();
                    System.out.println("Today's temperature for " + selectedState.getName() + "(Farenheit): ");
                    int todayTemperature = scanner.nextInt();
                    TodayWeather todayWeather = new TodayWeather();
                    todayWeather.setDate(new Date());
                    todayWeather.setTemperature(todayTemperature);
                    todayWeather.setDescription("Today's weather for " + selectedState.getName() + ", " + cAbbr);
                    weather.setTodayWeather(todayWeather);

                    //Wind input
                    System.out.println();
                    System.out.println("Wind speed: ");
                    int speed = scanner.nextInt();
                    System.out.println();
                    System.out.println("Wind direction: ");
                    int direction = scanner.nextInt();
                    Wind wind = new Wind();
                    wind.setWindSpeed(speed);
                    wind.setWindDirection(direction);
                    weather.setWind(wind);

                    //Atmosphere input
                    System.out.println();
                    System.out.println("Humidity: ");
                    int humidity = scanner.nextInt();
                    System.out.println();
                    System.out.println("Pressure: ");
                    float pressure = scanner.nextFloat();
                    System.out.println();
                    System.out.println("Visibility: ");
                    float visibility = scanner.nextFloat();
                    System.out.println();
                    System.out.println("Rising: ");
                    int rising = scanner.nextInt();
                    Atmosphere atmosphere = new Atmosphere();
                    atmosphere.setHumidity(humidity);
                    atmosphere.setPressure(pressure);
                    atmosphere.setVisibility(visibility);
                    atmosphere.setRising(rising);
                    weather.setAtmosphere(atmosphere);

                    //Forecast input
                    System.out.println();
                    System.out.println("Forecast for next 10 days.");
                    System.out.println();
                    Date sourceDate = weather.getTodayWeather().getDate();
                    Date myDate;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");

                    for (int i = 0; i < 10; i++) {
                        Forecast forecast = new Forecast();
                        myDate = DateUtil.addDays(sourceDate, i);
                        forecast.setDate(myDate);
                        String dayOfWeek = dateFormat.format(myDate);
                        forecast.setDay(dayOfWeek);
                        System.out.println("Max temperature for " + selectedState.getName() + " at " + myDate.toString() + " (Farenheit): ");
                        int high = scanner.nextInt();
                        forecast.setHigh(high);
                        System.out.println("Min temperature for " + selectedState.getName() + " at " + myDate.toString() + " (Farenheit): ");
                        int low = scanner.nextInt();
                        forecast.setLow(low);
                        forecast.setText("Weather for " + selectedState.getName() + " at " + myDate.toString());
                        weather.getForecasts()[i] = forecast;
                    }

                    try {
                        WeatherController.addWeatherItems(weather);
                        WeatherController.addWeather(weather, selectedState.getIdState());
                        WeatherController.addForecasts(weather);
                    } catch (Exception e) {
                        System.out.println("Error message: " + e.getMessage());
                        break;
                    }

                    //selectedState.setWeather(weather);
                    break;
                case 6:
                    //Selected country input
                    System.out.println();
                    System.out.println("3 letter country abbreviation: ");
                    String cAbb = scanner.next();
                    if (!CountryController.isAlreadyExists(cAbb)) {
                        System.out.println("Country does not exist.");
                        break;
                    }

                    /*Country seCountry = null;
                    for(Country co : countries){
                        if (co.getCountryID3().equalsIgnoreCase(cAbb)){
                            seCountry = co;
                        }
                    }
                    if (seCountry == null) {break;}*/

                    //Selected state input
                    System.out.println();
                    System.out.println("2 letter state abbreviation: ");
                    String staAbbr = scanner.next();
                    State sState = StateController.getState(cAbb, staAbbr);
                    if (sState == null) {
                        System.out.println("State does not exist.");
                        break;
                    }

                    /*State seState = null;
                    for (State st : seCountry.getStates()){
                        if (st.getAbbreviation().equalsIgnoreCase(staAbbr)){
                            seState = st;
                        }
                    }
                    if (seState == null) {break;}
                    System.out.println(seState.getWeather().toString());*/

                    System.out.println(WeatherController.getWeatherData(sState.getIdState()).toString());
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        /*
        Forecast f = new Forecast(1, new Date(), "", 20, 10, "test");
        Forecast[] forecasts = new Forecast[10];
        for (int i = 0; i < forecasts.length; i++) {
            forecasts[i] = f;
        }
        Atmosphere a = new Atmosphere(1, 1, 1f, 1f, 1);
        Wind w = new Wind(1, 2, 2);
        TodayWeather t = new TodayWeather(1, new Date(), 10, "test");
        Weather weather = new Weather(1,t,w,a,forecasts);
        State state = new State(1,"ARG","Cordoba","COR",10,"Cordoba",weather);
        List<State> states = new ArrayList<State>();
        states.add(state);
        Country c = new Country("Argentina","AR","ARG",states);

        System.out.println(c.toString());
        */

    }
}
