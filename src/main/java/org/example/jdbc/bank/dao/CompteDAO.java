package org.example.jdbc.bank.dao;

import org.example.jdbc.bank.model.Client;
import org.example.jdbc.bank.model.CompteBancaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompteDAO extends BaseDAO<CompteBancaire>{


    public CompteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(CompteBancaire element) throws SQLException {
        request = "INSERT INTO compte_bancaires (solde) VALUES (?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, element.getSolde());
        int nbRows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRows == 1;
    }

    @Override
    public boolean update(CompteBancaire element) throws SQLException {
        request = "UPDATE compte_bancaires SET solde = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setDouble(1, element.getSolde());
        statement.setInt(2,element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;    }

    @Override
    public boolean delete(CompteBancaire element) throws SQLException {
        request = "DELETE FROM compte_bancaires WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public CompteBancaire get(int id) throws SQLException {
        CompteBancaire compteBancaire = null;

        String request = "SELECT * FROM compte_bancaires WHERE id = ?";

        try (PreparedStatement statement = _connection.prepareStatement(request)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    compteBancaire = new CompteBancaire(
                            resultSet.getInt("id"),
                            resultSet.getDouble("solde")
                    );
                }
            }
        }
        return compteBancaire;
    }


    @Override
    public List<CompteBancaire> get() throws SQLException {
        List<CompteBancaire> result = new ArrayList<>();
        String request = "SELECT * FROM compte_bancaires";

        try (PreparedStatement statement = _connection.prepareStatement(request);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                CompteBancaire compteBancaire = new CompteBancaire(
                        resultSet.getInt("id"),
                        resultSet.getDouble("solde")
                        // Add other fields as necessary
                );
                result.add(compteBancaire);
            }
        }
        return result;
    }
}
