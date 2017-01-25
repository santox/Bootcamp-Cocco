package com.cocco.bootcamp.config;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by santi on 13/1/2017.
 */

public class DataSource {
    private Connection con;

    public DataSource(){

    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void openConnection(){
        try {
            String userName = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/bootcamp?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            System.out.println("Connection error at opening. Message: " + e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Connection error at closing. Message: " + e.getMessage());
        }
    }
}