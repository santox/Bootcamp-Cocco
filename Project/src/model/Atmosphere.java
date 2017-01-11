package model;

/**
 * Created by santi on 11/1/2017.
 */
public class Atmosphere {
    private int idForecast;
    private int humidity;
    private float pressure;
    private float visibility;
    private int rising;

    public Atmosphere() {
        idForecast = -1;
        humidity = -1;
        pressure = -1f;
        visibility = -1f;
        rising = -1;
    }

    public Atmosphere(int idForecast, int humidity, float pressure,
                      float visibility, int rising) {
        this.idForecast = idForecast;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.rising = rising;
    }

    public int getIdForecast() {
        return idForecast;
    }

    public void setIdForecast(int idForecast) {
        this.idForecast = idForecast;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getRising() {
        return rising;
    }

    public void setRising(int rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "\r\nHumidity=" + humidity +
                ", \r\nPressure=" + pressure +
                ", \r\nVisibility=" + visibility +
                ", \r\nRising=" + rising +
                '}';
    }
}
