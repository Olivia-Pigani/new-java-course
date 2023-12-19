package org.example.jdbc.bank.dao;

import org.example.jdbc.bank.model.Operation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends BaseDAO<Operation> {

    public OperationDAO(Connection connection) {
        super(connection);
    }


    @Override
    public boolean save(Operation element) throws SQLException {
        String request = "INSERT INTO operations (numero, montant, statut, id_compte) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, element.getNumero());
            statement.setDouble(2, element.getMontant());
            statement.setString(3, element.getOperationType());
            int nbRows = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    element.setId(resultSet.getInt(1));
                }
            }
            return nbRows == 1;
        }
    }

    @Override
    public boolean update(Operation element) throws SQLException {
        String request = "UPDATE operations SET numero = ?, montant = ?, statut = ? WHERE id = ?";
        try (PreparedStatement statement = _connection.prepareStatement(request)) {
            statement.setInt(1, element.getNumero());
            statement.setDouble(2, element.getMontant());
            statement.setString(3, element.getStatut());
            statement.setInt(4, element.getId());
            int nbRows = statement.executeUpdate();
            return nbRows == 1;
        }
    }

    @Override
    public boolean delete(Operation element) throws SQLException {
        String request = "DELETE FROM operations WHERE id = ?";
        try (PreparedStatement statement = _connection.prepareStatement(request)) {
            statement.setInt(1, element.getId());
            int nbRows = statement.executeUpdate();
            return nbRows == 1;
        }
    }

    @Override
    public Operation get(int id) throws SQLException {
        Operation operation = null;
        String request = "SELECT * FROM operations WHERE id = ?";
        try (PreparedStatement statement = _connection.prepareStatement(request)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    operation = new Operation(
                            resultSet.getInt("id"),
                            resultSet.getInt("numero"),
                            resultSet.getDouble("montant"),
                            resultSet.getString("statut"),
                            resultSet.getInt("id_compte")
                    );
                }
            }
        }
        return operation;
    }

    @Override
    public List<Operation> get() throws SQLException {
        List<Operation> operations = new ArrayList<>();
        String request = "SELECT * FROM operations";
        try (PreparedStatement statement = _connection.prepareStatement(request);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Operation operation = new Operation(
                        resultSet.getInt("id"),
                        resultSet.getInt("numero"),
                        resultSet.getDouble("montant"),
                        resultSet.getString("statut")
                );
                operations.add(operation);
            }
        }
        return operations;
    }
}
