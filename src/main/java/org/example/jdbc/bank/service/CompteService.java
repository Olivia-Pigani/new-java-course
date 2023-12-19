package org.example.jdbc.bank.service;

import org.example.jdbc.bank.dao.ClientDAO;
import org.example.jdbc.bank.dao.CompteDAO;
import org.example.jdbc.bank.model.Client;
import org.example.jdbc.bank.model.CompteBancaire;
import org.example.jdbc.bank.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompteService {

    private CompteDAO compteDAO;
    private Connection connection;



    public CompteService(){
        try {
            connection = new DatabaseManager().getConnection();
            compteDAO = new CompteDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean createCompte(Double solde){
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(solde);
        try {
            return compteDAO.save(compteBancaire);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateCompte(CompteBancaire compteBancaire){
        try {
            return compteDAO.update(compteBancaire);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CompteBancaire getCompte(int id){
        try {
            return compteDAO.get(id);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCompte(int id){
        CompteBancaire compteBancaire = null;
        try {
            compteBancaire = compteDAO.get(id);
            if(compteBancaire != null){
                return compteDAO.delete(compteBancaire);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<CompteBancaire> getAllComptes(){
        try {
            return compteDAO.get();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
