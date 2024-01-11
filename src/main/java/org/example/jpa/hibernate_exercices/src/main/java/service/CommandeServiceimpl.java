package service;

import dao.AdresseDAO;
import dao.CommandeDAO;
import interfaces.services.AdresseCRUD;
import interfaces.services.CommandCRUD;
import interfaces.services.CommentaryCRUD;
import interfaces.services.ProductCRUD;
import models.Adresse;
import models.Commande;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommandeServiceimpl implements AdresseCRUD<Adresse>, CommandCRUD<Commande> {


    private AdresseDAO adresseDAO;
    private CommandeDAO commandeDAO;

    public CommandeServiceimpl(AdresseDAO adresseDAO, CommandeDAO commandeDAO) {
        this.adresseDAO = adresseDAO;
        this.commandeDAO = commandeDAO;
    }

    // CRUD ADRESSE

    @Override
    public void addAdress(Adresse element) {

    }

    @Override
    public void deleteAdress(int id) {

    }

    @Override
    public List<Adresse> getAllAdresse() {
        return null;
    }

    @Override
    public Adresse getByIdAdresse(int id) {
        return null;
    }


    //CRUD COMMAND


    @Override
    public void addCommande(Commande element) {

    }

    @Override
    public void deleteCommande(int id) {

    }

    @Override
    public List<Commande> getAllCommande() {
       List<Commande> commandeList = commandeDAO.getAll();
       return commandeList;
    }

    @Override
    public Commande getByIdCommande(int id) {
        return null;
    }

    public List<Commande> getTodayCommand() {

        List<Commande> commandeList = commandeDAO.getTodayCommand();
        return commandeList;

    }
}
