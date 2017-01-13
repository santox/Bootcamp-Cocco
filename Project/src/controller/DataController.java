package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by santi on 13/1/2017.
 */
public class DataController {
    private Connection con;

    public void openConnection(){
        try {
            String userName = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/bootcamp?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            System.out.println("Connection error. Message: " + e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Connection error. Message: " + e.getMessage());
        }
    }
}
