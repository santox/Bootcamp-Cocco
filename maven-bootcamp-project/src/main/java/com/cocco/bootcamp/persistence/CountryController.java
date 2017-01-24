package com.cocco.bootcamp.persistence;

import com.cocco.bootcamp.config.DataSource;
import com.cocco.bootcamp.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santi on 18/1/2017.
 */
@Component
public class CountryController {
    //private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
    //private static DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static String addCountry(String name, String countryID2, String countryID3) {
        if (countryID2.length() != 2 || countryID3.length() != 3) {
            return "Country abbreviation incorrect, try again.";
        }
        String message = "Nothing happened.";
        boolean alreadyExists = isAlreadyExists(countryID3);
        String query;

        //If already exists Update, else Insert
        if (alreadyExists) {
            message = updateCountry(name, countryID2, countryID3);
        } else {
            query = "insert into country ("
                    + " country_name,"
                    + " country_id2,"
                    + " country_id3 ) values ("
                    + "?, ?, ?);";
            try {
                dataSource.openConnection();
                PreparedStatement statement = dataSource.getCon().prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, countryID2);
                statement.setString(3, countryID3);

                statement.executeUpdate();
                statement.close();
                message = name + " added!";
            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("SQL Exception during INSERT message: " + e.getMessage());
            } finally {
                dataSource.closeConnection();
            }
        }
        return message;
    }

    private static String updateCountry(String name, String countryID2, String countryID3) {
        String message = "Nothing happened.";
        String query;
        query = "update country"
                + " set country_name = ?,"
                + " country_id2 = ?"
                + " where country_id3 = ?;";
        try {
            dataSource.openConnection();
            PreparedStatement statement = dataSource.getCon().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, countryID2);
            statement.setString(3, countryID3);

            statement.executeUpdate();
            statement.close();
            message = name + " updated!";
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception during UPDATE message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }
        return message;
    }

    public static boolean isAlreadyExists(String countryID3) {
        //Check if country PK already exists
        boolean alreadyExists = false;
        String query = "select country_name" +
                " from country" +
                " where country_id3 = ?;";
        try {
            dataSource.openConnection();
            PreparedStatement statement = dataSource.getCon().prepareStatement(query);
            statement.setString(1, countryID3);

            ResultSet resultSet = statement.executeQuery();
            alreadyExists = resultSet.next();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception during SELECT message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }
        return alreadyExists;
    }

    public static List<Country> getCountries() {
        List<Country> countries = new ArrayList<Country>();
        String query = "select"
                + " country_name,"
                + " country_id2,"
                + " country_id3 from country;";
        try {
            dataSource.openConnection();
            PreparedStatement statement = dataSource.getCon().prepareStatement(query);
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
            //e.printStackTrace();
            System.out.println("SQL Exception during SELECT message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }

        return countries;
    }
}
