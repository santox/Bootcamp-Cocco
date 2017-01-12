package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 11/1/2017.
 */
public class Country {
    private String name;
    private String countryID2;
    private String countryID3;
    private List<State> states;

    public Country() {
        name = "";
        countryID2 = "";
        countryID3 = "";
        states = new ArrayList<State>();
    }

    public Country(String name, String countryID2, String countryID3, List<State> states) {
        this.name = name;
        this.countryID2 = countryID2;
        this.countryID3 = countryID3;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryID2() {
        return countryID2;
    }

    public void setCountryID2(String countryID2) {
        this.countryID2 = countryID2;
    }

    public String getCountryID3() {
        return countryID3;
    }

    public void setCountryID3(String countryID3) {
        this.countryID3 = countryID3;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public void addState(State state){
        this.states.add(state);
    }

    @Override
    public String toString() {
        /*String allStates = "{";
        for(State state : states){allStates += "\r\n" + state.toString() + '\'';}
        allStates += '}';
        */
        return "Country{" +
                "\r\nName='" + name + '\'' +
                ", \r\nabbr2='" + countryID2 + '\'' +
                ", \r\nabbr3='" + countryID3 + '\'' +
                //", \r\nStates=" + allStates +
                '}';
    }
}
