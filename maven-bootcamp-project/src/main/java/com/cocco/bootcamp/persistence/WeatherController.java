package com.cocco.bootcamp.persistence;

import com.cocco.bootcamp.config.DataSource;
import com.cocco.bootcamp.domain.Forecast;
import com.cocco.bootcamp.domain.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by santi on 19/1/2017.
 */
public class WeatherController {
    private static DataSource dataSource = DataSource.getInstance();

    public static String addWeatherItems(Weather weather) throws SQLException {
        String message = "Nothing happened.";
        PreparedStatement insertTodayWeather = null;
        PreparedStatement insertWind = null;
        PreparedStatement insertAtmosphere = null;
        Connection con = dataSource.openConnection();
        try {
            con.setAutoCommit(false);
            String insertTodayWeatherQuery = "insert into todayweather ("
                    + " todayweather_date,"
                    + " temperature,"
                    + " description"
                    + " ) values ("
                    + "?, ?, ?);";
            insertTodayWeather = con.prepareStatement(insertTodayWeatherQuery);
            //MySQL friendly datetime format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(weather.getTodayWeather().getDate());
            insertTodayWeather.setObject(1, date);
            insertTodayWeather.setInt(2, weather.getTodayWeather().getTemperature());
            insertTodayWeather.setString(3, weather.getTodayWeather().getDescription());
            insertTodayWeather.executeUpdate();

            String insertWindQuery = "insert into wind ("
                    + " speed,"
                    + " direction"
                    + " ) values ("
                    + "?, ?);";
            insertWind = con.prepareStatement(insertWindQuery);
            insertWind.setInt(1, weather.getWind().getWindSpeed());
            insertWind.setInt(2, weather.getWind().getWindDirection());
            insertWind.executeUpdate();

            String insertAtmosphereQuery = "insert into atmosphere ("
                    + " humidity,"
                    + " pressure,"
                    + " visibility,"
                    + " rising"
                    + " ) values ("
                    + "?, ?, ?, ?);";
            insertAtmosphere = con.prepareStatement(insertAtmosphereQuery);
            insertAtmosphere.setInt(1, weather.getAtmosphere().getHumidity());
            insertAtmosphere.setFloat(2, weather.getAtmosphere().getPressure());
            insertAtmosphere.setFloat(3, weather.getAtmosphere().getVisibility());
            insertAtmosphere.setInt(4, weather.getAtmosphere().getRising());
            insertAtmosphere.executeUpdate();

            con.commit();
            message = "Today weather, wind and atmosphere data added!";

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception message: " + e.getMessage());
            message = "Could not complete operation.";
            if (con != null) {
                try {
                    System.out.println("Transaction is being rolled back.");
                    con.rollback();
                } catch (SQLException e1) {
                    //e1.printStackTrace();
                    System.out.println("Rollback exception message: " + e1.getMessage());
                }
            }
        } finally {
            if (insertTodayWeather != null) {
                insertTodayWeather.close();
            }
            if (insertWind != null) {
                insertWind.close();
            }
            if (insertAtmosphere != null) {
                insertAtmosphere.close();
            }
            con.setAutoCommit(true);
            dataSource.closeConnection();
        }

        return message;
    }

    public static String addWeather(Weather weather, int stateID) {
        String message = "Nothing happened.";
        Connection con = dataSource.openConnection();
        int idTodayWeather;
        int idWind;
        int idAtmosphere;
        String query = "select"
                + " id_today_weather"
                + " from todayweather"
                + " order by id_today_weather desc limit 1;";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idTodayWeather = resultSet.getInt("id_today_weather");
                query = "select"
                        + " id_wind"
                        + " from wind"
                        + " order by id_wind desc limit 1;";
                statement = con.prepareStatement(query);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    idWind = resultSet.getInt("id_wind");
                    query = "select"
                            + " id_atmosphere"
                            + " from atmosphere"
                            + " order by id_atmosphere desc limit 1;";
                    statement = con.prepareStatement(query);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        idAtmosphere = resultSet.getInt("id_atmosphere");
                        query = "insert into weather ("
                                + " id_today_weather,"
                                + " id_wind,"
                                + " id_atmosphere,"
                                + " id_state"
                                + " ) values ("
                                + "?, ?, ?, ?);";
                        statement = con.prepareStatement(query);
                        statement.setInt(1, idTodayWeather);
                        statement.setInt(2, idWind);
                        statement.setInt(3, idAtmosphere);
                        statement.setInt(4, stateID);

                        statement.executeUpdate();
                        statement.close();
                        message = "Weather data added!";
                    }
                }
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }
        return message;
    }

    public static String addForecasts (Weather weather) {
        String message = "Nothing happened.";
        Connection con = dataSource.openConnection();
        int idWeather;
        String query = "select"
                + " id_weather"
                + " from weather"
                + " order by id_weather desc limit 1;";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idWeather = resultSet.getInt("id_weather");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Forecast f : weather.getForecasts()) {
                    query = "insert into forecasts ("
                            + " forecast_date,"
                            + " forecast_day,"
                            + " high,"
                            + " low,"
                            + " forecast_text,"
                            + " id_weather"
                            + " ) values ("
                            + "?, ?, ?, ?, ?, ?);";
                    statement = con.prepareStatement(query);
                    statement.setObject(1, dateFormat.format(f.getDate()));
                    statement.setString(2, f.getDay());
                    statement.setInt(3, f.getHigh());
                    statement.setInt(4, f.getLow());
                    statement.setString(5, f.getText());
                    statement.setInt(6, idWeather);
                    statement.executeUpdate();
                }
                message = "Forecasts added!";
                statement.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQL Exception message: " + e.getMessage());
        } finally {
            dataSource.closeConnection();
        }
        return message;
    }
}
