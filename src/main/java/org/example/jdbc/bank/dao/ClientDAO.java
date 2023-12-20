package org.example.jdbc.bank.dao;

import org.example.jdbc.bank.model.Client;
import org.example.jdbc.bank.model.CompteBancaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDAO extends BaseDAO<Client> {


    public ClientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Client element) throws SQLException {
        request = "INSERT INTO clients (nom, prenom,identifiant) VALUES (?, ?, ?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getPrenom());
        statement.setString(3, element.getIdentifiant());
        int nbRows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRows == 1;
    }

    @Override
    public boolean update(Client element) throws SQLException {
        request = "UPDATE clients SET nom = ?, prenom = ? , identifiant = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getPrenom());
        statement.setString(3, element.getIdentifiant());
        statement.setInt(4,element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;    }

    @Override
    public boolean delete(Client element) throws SQLException {
        request = "DELETE FROM clients WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public Client get(int id) throws SQLException {
        Client client = null;
        Map<Integer, CompteBancaire> accounts = new HashMap<>();

        String request = "SELECT * " +
                "FROM clients " +
                "LEFT JOIN compte_bancaires ON compte_bancaires.id_clients = clients.id " +
                "WHERE clients.id = ?";

        try (PreparedStatement statement = _connection.prepareStatement(request)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (client == null) {
                        client = new Client(
                                resultSet.getInt("id"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getString("identifiant"),
                                new ArrayList<>()
                        );
                    }
                    int accountId = resultSet.getInt("account_id");
                    if (!resultSet.wasNull()) {
                        CompteBancaire account = accounts.computeIfAbsent(accountId, k -> {
                            try {
                                return new CompteBancaire(
                                        accountId,
                                        resultSet.getDouble("solde")
                                );
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        client.getCompteBancaires().add(account);
                    }
                }
            }
        }
        return client;
    }


    @Override
    public List<Client> get() throws SQLException {
        List<Client> result = new ArrayList<>();
        request = "SELECT * FROM clients";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
             Client client = new Client(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                     resultSet.getString("identifiant")
                     );
            result.add(client);
        }
        System.out.println(result);
        return result;    }
}
