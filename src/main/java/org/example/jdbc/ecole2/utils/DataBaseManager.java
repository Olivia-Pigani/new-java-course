package org.example.jdbc.ecole2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private final String URI = "jdbc:mysql://localhost:3306/exercice1jdbc";
    private final String USER = "root";
    private final String PASSWORD = "test";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URI,USER,PASSWORD);
    }
}
