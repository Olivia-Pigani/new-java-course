package org.example.jdbc.billeterie.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.entities.Evenement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvenementDAO extends BaseDAO<Evenement> {


    protected EvenementDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Evenement> get() throws SQLException, ExecutionControl.NotImplementedException {
        List<Evenement> evenements = new ArrayList<>();
        request = "SELECT * FROM evenements";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Evenement evenement = new Evenement(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getDate("date"),
                    resultSet.getString("heure"),
                    resultSet.getFloat("prix")

            );
            evenements.add(evenement);
        }
        return evenements;
    }

    @Override
    public Evenement getById(int id) throws SQLException, ExecutionControl.NotImplementedException {
        Evenement evenement = null;
        request = "SELECT * FROM evenements WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            try {
                evenement = new Evenement(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDate("date"),
                        resultSet.getString("heure"),
                        resultSet.getFloat("prix") );
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return evenement;
    }

    @Override
    public boolean save(Evenement element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "INSERT INTO evenements (nom,date,heure,prix) VALUES (?,?,?,?)";


        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getNom());
        statement.setDate(2, new java.sql.Date(element.getDate().getTime())); // faire un formatter dans le ihm ?
        statement.setString(3, element.getHeure());
        statement.setFloat(4, element.getPrix());

        int nbRows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            try {
                element.setId(resultSet.getInt(1));
            } catch (Exception e) {
                System.out.println("erreur lors de la production de l'evenement");
            }


        }
        return nbRows == 1;

    }

    @Override
    public boolean update(Evenement element) throws SQLException, ExecutionControl.NotImplementedException {
        Evenement existingEvenement = getById(element.getId());

        if (existingEvenement == null) {
            return false;
        }

        request = "UPDATE evenements SET nom = ?, date = ?, heure = ?, prix = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNom());
        statement.setDate(2, new java.sql.Date(element.getDate().getTime()));
        statement.setString(3, element.getHeure());
        statement.setFloat(4, element.getPrix());
        statement.setInt(5, element.getId());

        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Evenement element) throws SQLException, ExecutionControl.NotImplementedException {
        Evenement evenement = getById(element.getId());
        if (evenement == null) {
            return false;
        }
        request = "DELETE FROM evenements WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();

        return nbRows == 1;

    }
}
