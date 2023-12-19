package org.example.jdbc.bank.service;

import org.example.jdbc.bank.dao.ClientDAO;
import org.example.jdbc.bank.model.Client;
import org.example.jdbc.bank.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDAO clientDAO;
    private Connection connection;


    public ClientService(){
        try {
            connection = new DatabaseManager().getConnection();
            clientDAO = new ClientDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean createClient(String nom,String prenom){
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        try {
            return clientDAO.save(client);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateClient(Client client){
        try {
            return clientDAO.update(client);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClient(int id){
        try {
            return clientDAO.get(id);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteClient(int id){
        Client client = null;
        try {
            client = clientDAO.get(id);
            if(client != null){
                return clientDAO.delete(client);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Client> getAllClients(){
        try {
            return clientDAO.get();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
