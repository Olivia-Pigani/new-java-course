package org.example.jdbc.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private final String BD_URL = "jdbc:mysql://localhost:3306/my_bank_jdbc";
    private final String BD_USER = "root";
    private final String BD_PASSWORD = "test";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(BD_URL,BD_URL,BD_PASSWORD);
    }
}
