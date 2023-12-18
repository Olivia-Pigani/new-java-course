package org.example.jdbc.ecole.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getMySqlConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ecole_jdbc";
        String userName = "root";
        String passWord = "test";
        return DriverManager.getConnection(url,userName,passWord);
    }


}
