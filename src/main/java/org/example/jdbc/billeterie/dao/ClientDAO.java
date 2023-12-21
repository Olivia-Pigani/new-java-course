package org.example.jdbc.billeterie.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.entities.Client;
import org.example.utils.connection.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client> {

    protected ClientDAO(Connection connection) {
        super(connection);
    }


    @Override
    public List<Client> get() throws SQLException, ExecutionControl.NotImplementedException {

        List<Client> clients = new ArrayList<>();

        try {


            request = "SELECT * FROM clients";
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"));
                clients.add(client);


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            DatabaseManager.closeConnection(); // utiliser le service à la place
        }
        return clients;

    }

    @Override
    public Client getById(int id) throws SQLException, ExecutionControl.NotImplementedException {

        Client client = null;

        try {


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


        } catch (Exception e) {
            System.out.println("erreur lors de la selection du client !");


        }
        return client;

    }

    @Override
    public void save(Client element) throws SQLException, ExecutionControl.NotImplementedException {

        try {

            _connection.setAutoCommit(false);


            request = "INSERT INTO clients (nom,prenom,email) VALUES (?,?,?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getNom());
            statement.setString(2, element.getPrenom());
            statement.setString(3, element.getEmail());
            int nbRows = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next() && nbRows==1) {
                _connection.commit();
                element.setId(resultSet.getInt(1));
            } else {
                _connection.rollback();
                System.out.println("transaction annulée ! ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseManager.closeConnection();
        }




    }

    @Override
    public void update(Client element) throws SQLException, ExecutionControl.NotImplementedException {
        Client existingClient = getById(element.getId());

        if (existingClient == null) {
            System.out.println("aucun client avec un tel id !");
        }

        try {
            _connection.setAutoCommit(false);

            request = "UPDATE clients SET nom = ?, prenom = ?, email = ? WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setString(1, element.getNom());
            statement.setString(2, element.getPrenom());
            statement.setString(3, element.getEmail());
            statement.setInt(4, element.getId());

            int nbRows = statement.executeUpdate();

            if (resultSet.next() && nbRows==1){
                _connection.commit();
                System.out.println("la transaction s'est bien déroulée  ! ");
            }


        }catch (Exception e){
            System.out.println(" il y a une erreur lors de la mise à jour du client ! ");
        }finally {
            DatabaseManager.closeConnection();
        }

    }

    @Override
    public void delete(Client element) throws SQLException, ExecutionControl.NotImplementedException {
        Client client = getById(element.getId());




        if (client == null) {
            System.out.println("pas de client avec un tel id ! ");
        }
        try {


            _connection.setAutoCommit(false);


            request = "DELETE FROM clients WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, element.getId());
            int nbRows = statement.executeUpdate();

            if (nbRows==1 && resultSet.next()){
                _connection.commit();
                System.out.println("la transaction s'est bien déroulée ! ");
            }else {
                _connection.rollback();
                System.out.println("la transaction a été annulée ! ");
            }


        }catch (Exception e){
            System.out.println(" il y a eu une erreur lors de la transaction du delete ! ");
        }finally {
            DatabaseManager.closeConnection();
        }





    }
}
