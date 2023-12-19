package org.example.jdbc.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String identifiant;
    List<CompteBancaire> compteBancaires;

    public Client(int id, String nom, String prenom, String identifiant) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;

    }

    public Client() {

    }
}
