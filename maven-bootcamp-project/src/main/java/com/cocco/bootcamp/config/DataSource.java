package com.cocco.bootcamp.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by santi on 13/1/2017.
 */
public class DataSource {
    private static DataSource ourInstance = new DataSource();
    private Connection con;

    public static DataSource getInstance() {
        return ourInstance;
    }

    private DataSource(){
    }

    public Connection openConnection(){
        try {
            String userName = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/bootcamp?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            System.out.println("Connection error at opening. Message: " + e.getMessage());
        }
        return con;
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Connection error at closing. Message: " + e.getMessage());
        }
    }
}
