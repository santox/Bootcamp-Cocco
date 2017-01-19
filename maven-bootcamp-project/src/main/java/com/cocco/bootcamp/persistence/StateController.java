package com.cocco.bootcamp.persistence;

import com.cocco.bootcamp.config.DataSource;
import com.cocco.bootcamp.domain.State;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 19/1/2017.
 */
public class StateController {
    private static DataSource dataSource = DataSource.getInstance();

    public static String addState (String countryID3, String stateName, String stateAbbr, long area, String capital) {
        String message = "Nothing happened.";
        String query;
        query = "insert into state ("
                + " country_id3,"
                + " state_name,"
                + " state_abbreviation,"
                + " area,"
                + " capital"
                + " ) values ("
                + "?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
            statement.setString(1, countryID3);
            statement.setString(2, stateName);
            statement.setString(3, stateAbbr);
            statement.setLong(4, area);
            statement.setString(5, capital);

            statement.executeUpdate();
            statement.close();
            message = stateName + " added to " + countryID3;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception during INSERT message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }

        return message;
    }

    public static List<State> getStates(String countryID3) {
        List<State> states = new ArrayList<State>();
        String query = "select"
                + " id_state,"
                + " country_id3,"
                + " state_name,"
                + " state_abbreviation,"
                + " area,"
                + " capital from state"
                + " where country_id3 = ?;";
        try {
            PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
            statement.setString(1, countryID3);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                State state = new State();
                state.setIdState(resultSet.getInt("id_state"));
                state.setCountryID3(resultSet.getString("country_id3"));
                state.setName(resultSet.getString("state_name"));
                state.setAbbreviation(resultSet.getString("state_abbreviation"));
                state.setArea(resultSet.getLong("area"));
                state.setCapital(resultSet.getString("capital"));
                states.add(state);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception during SELECT message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }
        return states;
    }

    public static State getState(String countryID3, String stateAbbreviation) {
        State state = null;
        String query = "select"
                + " id_state,"
                + " country_id3,"
                + " state_name,"
                + " state_abbreviation,"
                + " area,"
                + " capital from state"
                + " where country_id3 = ? and state_abbreviation = ?;";
        try {
            PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
            statement.setString(1, countryID3);
            statement.setString(2, stateAbbreviation);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                state = new State();
                state.setIdState(resultSet.getInt("id_state"));
                state.setCountryID3(resultSet.getString("country_id3"));
                state.setName(resultSet.getString("state_name"));
                state.setAbbreviation(resultSet.getString("state_abbreviation"));
                state.setArea(resultSet.getLong("area"));
                state.setCapital(resultSet.getString("capital"));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception during SELECT message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }

        return state;
    }

}
