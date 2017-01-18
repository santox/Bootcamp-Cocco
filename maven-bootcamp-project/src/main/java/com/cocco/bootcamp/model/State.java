package com.cocco.bootcamp.model;

/**
 * Created by santi on 11/1/2017.
 */
public class State {
    private int idState;
    private String countryID3;
    private String name;
    private String abbreviation;
    private long area;
    private String capital;
    private Weather weather;

    public State() {
        idState = -1;
        countryID3 = "";
        name = "";
        abbreviation = "";
        area = -1;
        capital = "";
        weather = new Weather();
    }

    public State(int idState, String countryID3, String name,
                 String abbreviation, long area, String capital, Weather weather) {
        this.idState = idState;
        this.countryID3 = countryID3;
        this.name = name;
        this.abbreviation = abbreviation;
        this.area = area;
        this.capital = capital;
        this.weather = weather;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getCountryID3() {
        return countryID3;
    }

    public void setCountryID3(String countryID3) {
        this.countryID3 = countryID3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "State{" +
                "\r\nCountry='" + countryID3 + '\'' +
                ", \r\nName='" + name + '\'' +
                ", \r\nAbbreviation='" + abbreviation + '\'' +
                ", \r\nArea=" + area + "KM" +
                ", \r\nCapital='" + capital + '\'' +
                //", \r\nWeather=" + weather +
                '}';
    }
}
