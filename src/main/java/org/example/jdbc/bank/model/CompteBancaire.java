package org.example.jdbc.bank.model;


import lombok.Data;

import java.util.List;

@Data
public class CompteBancaire {

    private int id;
    private double solde;
    private Client client;
    private List<Operation> operations;

    public CompteBancaire(int id, double solde, Client client, List<Operation> operations) {
        this.id = id;
        this.solde = solde;
        this.client = client;
        this.operations = operations;
    }


    public CompteBancaire(int accountId, double solde) {
        this.id = accountId;
        this.solde=solde;
    }

    public CompteBancaire() {

    }
}
