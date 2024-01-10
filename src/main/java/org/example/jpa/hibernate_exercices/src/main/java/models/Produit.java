package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "produit")
    private List<Commentaire> commentaireList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "produit_commande",
    joinColumns = @JoinColumn(name = "produit_id"),
    inverseJoinColumns = @JoinColumn(name = "commande_id"))
    private List<Commande> commandeList = new ArrayList<>();




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
