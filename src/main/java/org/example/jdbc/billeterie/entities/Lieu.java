package org.example.entities;

import lombok.Data;

@Data
public class Lieu {
    private int id;
    private String nom;
    private String adresse;
    private Integer capacite;

    public Lieu(int id, String nom, String adresse, Integer capacite) {
        this.id=id;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
    }

}
