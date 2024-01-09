package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "produits")
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marque;

    private String reference;

    private Date dateAchat;

    private double prix;

    private int stock;

    public Produit() {
    }

    public Produit(String marque, String reference, Date dateAchat, double prix, int stock) {
        this.marque = marque;
        this.reference = reference;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.stock = stock;
    }
}
