package org.example.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.entities.Client;
import org.example.utils.connection.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends org.example.dao.BaseDAO<Client> {

    protected ClientDAO(Connection connection) {
        super(connection);
    }


    @Override
    public List<Client> get() throws SQLException, ExecutionControl.NotImplementedException {

        List<Client> clients = new ArrayList<>();

        try {

            _connection.setAutoCommit(false);


            request = "SELECT * FROM clients";
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"));
                clients.add(client);


                _connection.commit();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                _connection.rollback();
                System.out.println("transaction annulée ! ");
            } catch (SQLException er) {
                System.out.println("erruer lors de l'annulation de la transaction ! " + er.getMessage());
            }

        } finally {
            DatabaseManager.closeConnection(); // utiliser le service à la place
        }
        return clients;

    }

    @Override
    public Client getById(int id) throws SQLException, ExecutionControl.NotImplementedException {

        Client client = null;

        try {

            _connection.setAutoCommit(false);

            request = "SELECT * FROM clients WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                client = new Client(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"));

            }

            _connection.commit();

        } catch (Exception e) {
            System.out.println("erreur lors de la selection du client !");

            try {
                _connection.rollback();
                System.out.println("annulation de la transaction de la selection du client ! ");
            } catch (SQLException er) {
                System.out.println("erreur lors de l'anuation de la transation ! " + er.getMessage());
            } finally {
                DatabaseManager.closeConnection();
            }

        }
        return client;

    }

    @Override
    public boolean save(Client element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "INSERT INTO clients (nom,prenom,email) VALUES (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getPrenom());
        statement.setString(3, element.getEmail());
        int nbRows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            try {
                element.setId(resultSet.getInt(1));
            } catch (Exception e) {
                System.out.println("erreur lors de la production de client");
            }


        }
        return nbRows == 1;


    }

    @Override
    public boolean update(Client element) throws SQLException, ExecutionControl.NotImplementedException {
        Client existingClient = getById(element.getId());

        if (existingClient == null) {
            return false;
        }

        request = "UPDATE clients SET nom = ?, prenom = ?, email = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNom());
        statement.setString(2, element.getPrenom());
        statement.setString(3, element.getEmail());
        statement.setInt(4, element.getId());

        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Client element) throws SQLException, ExecutionControl.NotImplementedException {
        Client client = getById(element.getId());
        if (client == null) {
            return false;
        }
        request = "DELETE FROM clients WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();

        return nbRows == 1;


    }
}
