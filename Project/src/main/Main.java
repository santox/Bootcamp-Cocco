package main;

import model.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

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

    }
}
