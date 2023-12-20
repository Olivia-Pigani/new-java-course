package org.example.jdbc.bank.util;

import org.example.jdbc.bank.model.Client;
import org.example.jdbc.bank.model.CompteBancaire;
import org.example.jdbc.bank.service.ClientService;
import org.example.jdbc.bank.service.CompteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankIHM {


    private static ClientService clientService = new ClientService();
    private static CompteService compteService = new CompteService();
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        boolean running = true;
        while (running) {
            System.out.println("1 - Nouveau client");
            System.out.println(" 2 - Modifier client");
            System.out.println(" 3 - Supprimer un client");
            System.out.println(" 4 - Afficher tous les clients");
            System.out.println(" 5 - Gestion d'un client");
            System.out.println(" 6 - Quitter");
            System.out.println(" Choix => ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    updateClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    displayAllClients();
                    break;

                case 5:
                    clientAccountManagement();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }


            System.out.println(" fin du programme ! ");


        }
    }

    public static void accountMenu() {
        boolean running = true;
        while (running) {
            System.out.println("nouveau compte");
            System.out.println("afficher un compte, dont le solde et les opérations ");
            System.out.println("afficher tout les comptes d'un client");
            System.out.println("effectuer un dépot selon un compte");
            System.out.println("effectuer un retrait selon un compte");
            System.out.println("supprimer un compte");
            System.out.println("quitter le menu");
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                produceAnAccount();
                break;
            case 2:
                displayOneAccount();
                break;
            case 3:
                displayAllAccountsOfAClient();
                break;
            case 4:
                makeADeposit();
                break;

            case 5:
                makeAWithdraw();
                break;
            case 5:
                deleteAnAccount();
                break;
            case 6:
                running = false;
                break;
            default:
                System.out.println("Choix invalide !");
        }


    }

    private static void deleteAnAccount() {

        System.out.println(" quel est le numéro de de compte ?");
        int numeroDeCompte = scanner.nextInt();
        scanner.nextLine();
        CompteBancaire compteBancaire = compteService.getCompte(numeroDeCompte);
        if (compteBancaire != null) {

           compteService.deleteCompte(numeroDeCompte);


        } else {
            System.out.println("aucun compte avec ce numéro ! ");
        }

    }

    private static void displayOneAccount() {


        System.out.println(" quel est l'id du client ?");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = clientService.getClient(id);
        if (client != null) {

            System.out.println("Quel est le numéro du compte ?");
            int numeroAccount = scanner.nextInt();
            scanner.nextLine();
            if (numeroAccount != null) {
                List<CompteBancaire> compteBancaires = compteService.getCompte(numeroAccount);
                for (CompteBancaire compte : compteBancaires
                ) {
                    System.out.println(compte);
                }

            } else {
                System.out.println(" Aucun compte avec ce numéro ! ");
            }


        } else {
            System.out.println("aucun client avec cet id ! ");
        }


    }

    private static void displayAllAccountsOfAClient() {

        System.out.println(" quel est l'id du client ?");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = clientService.getClient(id);
        if (client != null) {

            List<CompteBancaire> compteBancaires = compteService.getAllComptes(id);
            for (CompteBancaire compte : compteBancaires
            ) {
                System.out.println(compte);
            }


        } else {
            System.out.println("aucun client avec cet id ! ");
        }
    }

//    ACCOUNT

    private static void produceAnAccount() {


        System.out.println(" quel est l'id du client ?");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = clientService.getClient(id);
        if (client != null) {
            System.out.println("quel est le solde de départ ? ");
            double startCash = scanner.nextDouble();

            compteService.createCompte();
        } else {
            System.out.println("aucun client avec cet id ! ");
        }


    }


//    CLIENT

    private static void clientAccountManagement() {
        System.out.println("Quel est l'id du client ? ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = clientService.getClient(id);
        if (client != null) {
            accountMenu();
        } else {
            System.out.println("pas de client avec un tel id trouvé ! ");
        }

    }


    public static void createClient() {
        System.out.println("le nom du client :");
        String newClientlN = scanner.nextLine();
        System.out.println("le prenom du client :");
        String newClientfN = scanner.nextLine();
        System.out.println("l'identifiant du client :");
        String newClientIdentifiant = scanner.nextLine();

        clientService.createClient(newClientlN, newClientfN, newClientIdentifiant);
    }

    ;


    public static void updateClient() {
        System.out.println("Quel est l'id du client à modifier ? ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClient(id);
        if (client != null) {
            System.out.println("client non trouvé ! ");

        }

        System.out.println("le nouveau nom de famille");
        String newlN = scanner.nextLine();
        System.out.println("le nouveau prénom");
        String newfN = scanner.nextLine();
        System.out.println("le nouvel identifiant");
        String newIdentifiant = scanner.nextLine();

        client.setNom(newlN);
        client.setPrenom(newfN);
        client.setIdentifiant(newIdentifiant);

        clientService.updateClient(client);
        System.out.println("client mis à jour avec succès !");


    }


    public static void deleteClient() {
        System.out.println("Quel est l'id du client à supprimer ? ");
        int idToErase = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClient(idToErase);

        if (client != null) {
            clientService.deleteClient(idToErase);
            System.out.println(" le client à été supprimé avc succès ! ");
        } else {
            System.out.println("aucun client avec cet id à supprimer ! ");
        }
    }

    public static void displayAllClients() {
        List<Client> clients = clientService.getAllClients();
        for (Client client : clients
        ) {
            System.out.println(client);
        }
    }
}



























