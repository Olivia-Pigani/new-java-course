package org.example.utils.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/billeterie_jdbc";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "test";

    private static Connection connection;

    private DatabaseManager() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("production d'une création");
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public static void closeConnection() {

        if (connection!=null){
            System.out.println("fermeture de la connection ! ");
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connection = null;
            }
        }


    }
}
