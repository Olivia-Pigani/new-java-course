package org.example.jdbc.ecole2.entity;

import org.example.jdbc.ecole2.utils.DataBaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int classNumber;
    private Date dateDegree;

    public static String request;

    public static Connection connection;

    public static PreparedStatement statement;

    public Student(String firstName, String lastName, int classNumber, Date dateDegree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classNumber = classNumber;
        this.dateDegree = dateDegree;
    }

    public Student(int id, String firstName, String lastName, int classNumber, Date dateDegree) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classNumber = classNumber;
        this.dateDegree = dateDegree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public Date getDateDegree() {
        return dateDegree;
    }

    public void setDateDegree(Date dateDegree) {
        this.dateDegree = dateDegree;
    }

    public boolean save() throws SQLException {
        request = "INSERT INTO student (first_name,last_name,class_number,date_degree) VALUES (?,?,?,?)";
        // ouvrir la connection
        connection = new DataBaseManager().getConnection();
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,getFirstName());
        statement.setString(2,getLastName());
        statement.setInt(3,getClassNumber());
        statement.setDate(4,new java.sql.Date(getDateDegree().getTime()));
        int rowNb = statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            setId(resultSet.getInt(1));
        }
        statement.close();
        // Fermer la connexion a la bdd
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return rowNb > 0;
    }

    public boolean delete() throws SQLException {
        request = "DELETE FROM student WHERE id = ?";
        connection = new DataBaseManager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1,getId());
        int rowNb = statement.executeUpdate();
        statement.close();
        // Fermer la connexion a la bdd
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return rowNb > 0;
    }

    public static Student getById(int id) throws SQLException {
        Student student = null;
        request = "SELECT * FROM student WHERE id = ?";
        connection = new DataBaseManager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            student = new Student(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                    resultSet.getInt("class_number"),resultSet.getDate("date_degree"));
        }
        statement.close();
        // Fermer la connexion a la bdd
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return student;

    }

    public static List<Student> getAll() throws SQLException{
        List<Student> result = new ArrayList<>();
        request = "SELECT * FROM student";
        connection = new DataBaseManager().getConnection();
        statement = connection.prepareStatement(request);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student s = new Student(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                    resultSet.getInt("class_number"),resultSet.getDate("date_degree"));
            result.add(s);
        }
        statement.close();
        // Fermer la connexion a la bdd
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<Student> getByClass(int classNumber) throws SQLException{
        List<Student> result = new ArrayList<>();
        request = "SELECT * FROM student WHERE class_number = ?";
        connection = new DataBaseManager().getConnection();
        statement = connection.prepareStatement(request);
        statement.setInt(1, classNumber);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student s = new Student(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                    resultSet.getInt("class_number"),resultSet.getDate("date_degree"));
            result.add(s);
        }
        statement.close();
        // Fermer la connexion a la bdd
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", classNumber=" + classNumber +
                ", dateDegree=" + dateDegree +
                '}';
    }
}
