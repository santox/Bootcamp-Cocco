package com.cocco.bootcamp.weatherAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by santi on 3/2/2017.
 */
public class WeatherItem {
    private String title;
    private String lat;
    @JsonProperty("long")
    private String longitude;
    private String link;
    private String pubDate;
    private WeatherCondition condition;
    private List<WeatherExtended> forecast;
    private String description;
    @JsonIgnore
    private Object guid;

    public WeatherItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    public List<WeatherExtended> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherExtended> forecast) {
        this.forecast = forecast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getGuid() {
        return guid;
    }

    public void setGuid(Object guid) {
        this.guid = guid;
    }
}
