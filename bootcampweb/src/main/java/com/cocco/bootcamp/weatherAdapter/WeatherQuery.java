package com.cocco.bootcamp.weatherAdapter;

/**
 * Created by santi on 3/2/2017.
 */
public class WeatherQuery {
    private int count;
    private String created;
    private String lang;
    private WeatherResults results;

    public WeatherQuery() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public WeatherResults getResults() {
        return results;
    }

    public void setResults(WeatherResults results) {
        this.results = results;
    }
}
