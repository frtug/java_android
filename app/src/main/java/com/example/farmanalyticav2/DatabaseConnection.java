package com.example.farmanalyticav2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String host = "localhost";
        int port = 3306;
        String database = "u909068228_farmanfarm";
        String username = "u909068228_farmanfarm";
        String password = "FarmAnalytica2023";

        String url = "jdbc:mysql://" + "localhost" + ":" + 3306 + "/" + "u909068228_farmanfarm";

        return DriverManager.getConnection(url, username, password);
    }

}
