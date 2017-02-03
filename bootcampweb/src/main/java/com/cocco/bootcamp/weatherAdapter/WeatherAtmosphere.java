package com.cocco.bootcamp.weatherAdapter;

/**
 * Created by santi on 3/2/2017.
 */
public class WeatherAtmosphere {
    private String humidity;
    private String pressure;
    private String rising;
    private String visibility;

    public WeatherAtmosphere() {
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
