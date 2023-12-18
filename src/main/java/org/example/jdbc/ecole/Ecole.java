package org.example.jdbc.ecole;

import org.example.jdbc.ecole.utils.ConnectionUtil;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Ecole {


    public static Scanner scanner = new Scanner(System.in);

    public static void insertStudent() {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getMySqlConnection();
            System.out.println("connexion ok !");

            System.out.println("saisissez le nom ");
            String nom = scanner.nextLine();
            System.out.println("saisissez le prenom ");
            String prenom = scanner.nextLine();
            System.out.println("saisissez le numeroClasse ");
            int numeroClasse = scanner.nextInt();
            System.out.println("saisissez la dateDiplome ");
            scanner.nextLine();
            String dateDiplome = scanner.nextLine();

            String request = "INSERT INTO etudiants (nom,prenom,nb_classe,date_diplome) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, numeroClasse);
            preparedStatement.setString(4, dateDiplome);
            int nbRows = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println("nombre de ligne => " + nbRows);
            if (resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Fermer la connexion a la bdd
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }


    }


    public static void allStudents() {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getMySqlConnection();
            System.out.println("connexion ok !");


            String request = "SELECT * FROM etudiants";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+") "+resultSet.getString("nom")+" "+resultSet.getString("prenom")+" " + resultSet.getInt("nb_classe") + " " + resultSet.getString("date_diplome"));
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Fermer la connexion a la bdd
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }



    public static void allStudentsByClass(int classNb) {

        String query = "SELECT id, nom, prenom, date_diplome FROM etudiants WHERE nb_classe = ?";

        try (
                Connection connection = ConnectionUtil.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("connexion ok !");

            preparedStatement.setInt(1, classNb);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + ") " + resultSet.getString("nom") + " " + resultSet.getString("prenom") + " " + resultSet.getString("date_diplome"));
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }


    public static void deleteAStudent(int studentId) {
        String query = "DELETE FROM etudiants WHERE id = ?";

        try (
                Connection connection = ConnectionUtil.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Eleve avec l'id => " + studentId + " a été supprimé !");
            } else {
                System.out.println("pas de éleve avec l'id en question");
            }

        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }


}















