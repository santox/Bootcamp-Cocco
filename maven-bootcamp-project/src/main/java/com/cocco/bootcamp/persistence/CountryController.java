package com.cocco.bootcamp.persistence;

import com.cocco.bootcamp.config.DataSource;
import com.cocco.bootcamp.domain.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 18/1/2017.
 */
public class CountryController {
    DataSource dataSource = DataSource.getInstance();

    public boolean addCountry(String name, String countryID2, String countryID3) {
        boolean alreadyExists = false;
        boolean inserted = false;
        //Check if country PK already exists
        String query = "select country_name from country where country_id3 = ?;";
        try {
            PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
            statement.setString(1, countryID3);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                alreadyExists = true;
            } else {
                alreadyExists = false;
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.closeConnection();
        }

        //If already exists Update, else Insert
        if (alreadyExists) {
            query = "update country"
                    + " set country_name = ?,"
                    + " country_id2 = ?"
                    + " where country_id3 = ?;";
            try {
                PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, countryID2);
                statement.setString(3, countryID3);

                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dataSource.closeConnection();
            }
        } else {
            query = "insert into country ("
                    + " country_name,"
                    + " country_id2,"
                    + " country_id3 ) values ("
                    + "?, ?, ?);";
            try {
                PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, countryID2);
                statement.setString(3, countryID3);

                statement.executeUpdate();
                statement.close();
                inserted = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dataSource.closeConnection();
            }
        }
        return inserted;
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<Country>();
        String query = "select"
                + " country_name,"
                + " country_id2,"
                + " country_id3 from country;";
        try {
            PreparedStatement statement = dataSource.openConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setName(resultSet.getString("country_name"));
                country.setCountryID2(resultSet.getString("country_id2"));
                country.setCountryID3(resultSet.getString("country_id3"));
                countries.add(country);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.closeConnection();
        }

        return countries;
    }
}
