package com.cocco.bootcamp.weatherAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by santi on 3/2/2017.
 */
public class WeatherChannel {
    @JsonIgnore
    private Object units;
    private String title;
    private String link;
    private String description;
    private String language;
    private String lastBuildDate;
    private String ttl;
    @JsonIgnore
    private Object location;
    private WeatherWind wind;
    private WeatherAtmosphere atmosphere;
    @JsonIgnore
    private Object astronomy;
    @JsonIgnore
    private Object image;
    private WeatherItem item;

    public WeatherChannel() {
    }

    public Object getUnits() {
        return units;
    }

    public void setUnits(Object units) {
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public WeatherWind getWind() {
        return wind;
    }

    public void setWind(WeatherWind wind) {
        this.wind = wind;
    }

    public WeatherAtmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(WeatherAtmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Object getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Object astronomy) {
        this.astronomy = astronomy;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public WeatherItem getItem() {
        return item;
    }

    public void setItem(WeatherItem item) {
        this.item = item;
    }
}
