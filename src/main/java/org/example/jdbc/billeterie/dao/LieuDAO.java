package org.example.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.entities.Evenement;
import org.example.entities.Lieu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO extends org.example.dao.BaseDAO<Lieu> {


    protected LieuDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Lieu> get() throws SQLException, ExecutionControl.NotImplementedException {
        List<Lieu> lieus = new ArrayList<>();
        request = "SELECT * FROM lieu";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Lieu lieu = new Lieu(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("adresse"),
                    resultSet.getInt("capacite")

            );
            lieus.add(lieu);
        }
        return lieus;
    }

    @Override
    public Lieu getById(int id) throws SQLException, ExecutionControl.NotImplementedException {
        Lieu lieu = null;
        request = "SELECT * FROM lieu WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            try {
                lieu = new Lieu(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getInt("capacite")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return lieu;
    }

    @Override
    public boolean save(Lieu element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "INSERT INTO lieu (nom,adresse,capacite) VALUES (?,?,?)";


        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getAdresse());
        statement.setInt(3, element.getCapacite());

        int nbRows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            try {
                element.setId(resultSet.getInt(1));
            } catch (Exception e) {
                System.out.println("erreur lors de la production du lieu");
            }


        }
        return nbRows == 1;
    }

    @Override
    public boolean update(Lieu element) throws SQLException, ExecutionControl.NotImplementedException {
        Lieu existingLieu= getById(element.getId());

        if (existingLieu == null) {
            return false;
        }

        request = "UPDATE lieu SET nom = ?, adresse = ?, capacite = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getAdresse());
        statement.setInt(3, element.getCapacite());
        statement.setInt(4, element.getId());

        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Lieu element) throws SQLException, ExecutionControl.NotImplementedException {
        Lieu lieu = getById(element.getId());
        if (lieu == null) {
            return false;
        }
        request = "DELETE FROM lieu WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();

        return nbRows == 1;
    }
}
